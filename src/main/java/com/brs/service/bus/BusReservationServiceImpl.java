package com.brs.service.bus;

import com.brs.dto.mapper.TicketMapper;
import com.brs.dto.mapper.TripMapper;
import com.brs.dto.mapper.TripScheduleMapper;
import com.brs.dto.model.bus.*;
import com.brs.dto.model.user.UserDto;
import com.brs.exception.BRSException;
import com.brs.exception.EntityType;
import com.brs.exception.ExceptionType;
import com.brs.model.bus.*;
import com.brs.model.user.UserAccount;
import com.brs.repository.bus.*;
import com.brs.repository.user.UserRepository;
import com.brs.util.RandomStringUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BusReservationServiceImpl implements BusReservationService {

    @Autowired
    private StopRepository stopRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AgencyRepository agencyRepository;

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private TripScheduleRepository tripScheduleRepository;

    @Autowired
    private TicketRepository ticketRepository;


    /**
     * Retruns all the available stops in the database.
     *
     * @return
     */
    @Override
    public Set<StopDto> getAllStops() {
        return stopRepository.findAll()
                .stream()
                .map(stop -> modelMapper.map(stop, StopDto.class))
                .collect(Collectors.toCollection(TreeSet::new));
    }


    /**
     * Returns the Stop details based on stop code.
     *
     * @param stopCode
     * @return
     */
    @Override
    public StopDto getStopByCode(String stopCode){
        Optional<Stop> stop = Optional.ofNullable(stopRepository.findByCode(stopCode));
        if(stop.isPresent()) {
            return modelMapper.map(stop.get(), StopDto.class);
        }
        throw exception(EntityType.STOP, ExceptionType.ENTITY_NOT_FOUND, stopCode);
    }


    /**
     * Fetch AgencyDto from userDto
     *
     * @param userDto
     * @return
     */
    @Override
    public AgencyDto getAgency(UserDto userDto) {
        UserAccount user = getUser(userDto.getEmail());
        if(user != null) {
            Optional<Agency> agency = Optional.ofNullable(agencyRepository.findByOwner(user));
            if(agency.isPresent()) {
                return modelMapper.map(agency.get(), AgencyDto.class);
            }
            throw exceptionWithId(EntityType.AGENCY, ExceptionType.ENTITY_NOT_FOUND, 2L, user.getEmail());
        }
        throw exception(EntityType.USER, ExceptionType.ENTITY_NOT_FOUND, userDto.getEmail());
    }

    /**
     * Register a new agency from the Admin signup flow
     *
     * @param agencyDto
     * @return
     */
    @Override
    public AgencyDto addAgency(AgencyDto agencyDto) {
        UserAccount admin = getUser(agencyDto.getOwner().getEmail());
        if(admin != null) {
            Optional<Agency> agency = Optional.ofNullable(agencyRepository.findByName(agencyDto.getName()));
            if(!agency.isPresent()) {
                Agency agencyModel = new Agency();
                agencyModel.setName(agencyDto.getName());
                agencyModel.setDetails(agencyDto.getDetails());
                agencyModel.setCode(RandomStringUtil.getAlphaNumericString(8,agencyDto.getName()));
                agencyModel.setOwner(admin);
                agencyRepository.save(agencyModel);
                return modelMapper.map(agencyModel, AgencyDto.class);
            }
            throw exception(EntityType.AGENCY, ExceptionType.DUPLICATE_ENTITY, agencyDto.getName());
        }
        throw exception(EntityType.USER, ExceptionType.ENTITY_NOT_FOUND, agencyDto.getOwner().getEmail());
    }

    /**
     * Updates the agency with given Bus information
     *
     * @param agencyDto
     * @param busDto
     * @return
     */

    @Override
    @Transactional
    public AgencyDto updateAgency(AgencyDto agencyDto, BusDto busDto) {
        Agency agency = getAgency(agencyDto.getCode());
        if(agency != null) {
            if(busDto != null) {
                Optional<Bus> bus = Optional.ofNullable(busRepository.findByCodeAndAgency(busDto.getCode(), agency));
                if(!bus.isPresent()) {
                    Bus busModel = new Bus();
                    busModel.setAgency(agency);
                    busModel.setCode(busDto.getCode());
                    busModel.setCapacity(busDto.getCapacity());
                    busModel.setMake(busDto.getMake());
                    busRepository.save(busModel);
                    if(agency.getBuses() == null) {
                        agency.setBuses(new ArrayList<>());
                    }
                    agency.getBuses().add(busModel);
                    return modelMapper.map(agencyRepository.save(agency), AgencyDto.class);
                }
                throw exceptionWithId(EntityType.BUS, ExceptionType.DUPLICATE_ENTITY, 2L, busDto.getCode(),
                        agencyDto.getCode());
            } else {
                agency.setName(agencyDto.getName());
                agency.setDetails(agencyDto.getDetails());
                agency.setCode(agencyDto.getCode());
                return modelMapper.map(agencyRepository.save(agency), AgencyDto.class);
            }
        }
        throw  exceptionWithId(EntityType.AGENCY, ExceptionType.ENTITY_NOT_FOUND, 2L,
                agencyDto.getOwner().getEmail());
    }


    /**
     * Returns trip details basd on trip_id
     *
     * @param tripId
     * @return
     */
    @Override
    public TripDto getTripById(Long tripId) {
        Optional<Trip> trip = tripRepository.findById(tripId);
        if(trip.isPresent()) {
            return TripMapper.toTripDto(trip.get());
        }
        throw exception(EntityType.TRIP, ExceptionType.ENTITY_NOT_FOUND, tripId.toString());
    }


    /**
     * Creates two new Trips with the given information in tripDto object
     *
     * @param tripDto
     * @return
     */
    @Override
    @Transactional
    public List<TripDto> addTrip(TripDto tripDto) {
        Stop sourceStop = getStop(tripDto.getSourceStopCode());
        if(sourceStop != null) {
            Stop destinationStop = getStop(tripDto.getDestinationStopCode());
            if(destinationStop != null) {
                if(!sourceStop.getCode().equalsIgnoreCase(destinationStop.getCode())) {
                    Agency agency = getAgency(tripDto.getAgencyCode());
                    if(agency != null) {
                        Bus bus = getBus(tripDto.getBusCode());
                        if(bus != null) {
                            List<TripDto> trips = new ArrayList<>(2);
                            Trip toTrip = new Trip();
                            toTrip.setSourceStop(sourceStop);
                            toTrip.setDestStop(destinationStop);
                            toTrip.setAgency(agency);
                            toTrip.setBus(bus);
                            toTrip.setJourneyTime(tripDto.getJourneyTime());
                            toTrip.setFare(tripDto.getFare());
                            trips.add(TripMapper.toTripDto(tripRepository.save(toTrip)));

                            Trip fromTrip = new Trip();
                            fromTrip.setSourceStop(destinationStop);
                            fromTrip.setDestStop(sourceStop);
                            fromTrip.setBus(bus);
                            fromTrip.setAgency(agency);
                            fromTrip.setFare(tripDto.getFare());
                            fromTrip.setJourneyTime(tripDto.getJourneyTime());
                            trips.add(TripMapper.toTripDto(tripRepository.save(fromTrip)));

                            return trips;
                        }
                        throw exception(EntityType.BUS, ExceptionType.ENTITY_NOT_FOUND, tripDto.getBusCode());
                    }
                    throw exception(EntityType.AGENCY, ExceptionType.ENTITY_NOT_FOUND, tripDto.getAgencyCode());
                }
                throw  exception(EntityType.TRIP, ExceptionType.ENTITY_EXCEPTION, "");
            }
            throw  exception(EntityType.STOP, ExceptionType.ENTITY_NOT_FOUND, tripDto.getDestinationStopCode());
        }
        throw exception(EntityType.STOP, ExceptionType.ENTITY_NOT_FOUND, tripDto.getSourceStopCode());
    }


    /**
     * Fetch all the trips for a given agency
     *
     * @param agencyCode
     * @return
     */
    @Override
    public List<TripDto> getAgencyTrips(String agencyCode) {
        Agency agency = getAgency(agencyCode);
        if(agency != null) {
            List<Trip> agencyTrips = tripRepository.findByAgency(agency);
            if(!agencyTrips.isEmpty()) {
                return agencyTrips
                        .stream()
                        .map(trip -> modelMapper.map(trip, TripDto.class))
                        .collect(Collectors.toList());
            }
            return Collections.emptyList();
        }
        throw exception(EntityType.AGENCY, ExceptionType.ENTITY_NOT_FOUND, agencyCode);
    }

    /**
     * Returns a list of trips between given source and destination stops.
     *
     * @param sourceStopCode
     * @param destinationStopCode
     * @return
     */
    @Override
    public List<TripDto> getAvailableTripsBetweenStops(String sourceStopCode, String destinationStopCode) {
        List<Trip> availableTrips = findTripsBetweenStops(sourceStopCode, destinationStopCode);
        if(!availableTrips.isEmpty()) {
            return availableTrips
                    .stream()
                    .map(trip -> TripMapper.toTripDto(trip))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }


    /**
     * Function to locate all the trips between src and dest stops and then
     * filter the results as per the given date based on data present in
     * trip schedule collection.
     *
     * @param sourceStopCode
     * @param destinationStopCode
     * @param tripDate
     * @return list of tripschedules on given date
     */
    @Override
    public List<TripScheduleDto> getAvailableTripSchedules(String sourceStopCode, String destinationStopCode,
                                                           Date tripDate) {
        List<Trip> availableTrips = findTripsBetweenStops(sourceStopCode, destinationStopCode);
        if(!availableTrips.isEmpty()) {
            return availableTrips
                    .stream().map(trip -> getTripSchedule(TripMapper.toTripDto(trip), tripDate, true))
                    .filter(tripScheduleDto -> tripScheduleDto != null)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }


    /**
     * Returns TripScheduleDto based on trip details and trip date,
     * optionally creates a schedule if its not found and if the createSchedForTrip
     * parameter is set to true.
     *
     * @param tripDto
     * @param tripDate
     * @param createSchedForTrip
     * @return
     */
    @Override
    public TripScheduleDto getTripSchedule(TripDto tripDto, Date tripDate, boolean createSchedForTrip) {
        Optional<Trip> trip = tripRepository.findById(tripDto.getId());
        if(trip.isPresent()) {
            Optional<TripSchedule> tripSchedule = Optional.ofNullable(tripScheduleRepository
                    .findByDetailAndTripDate(trip.get(), tripDate));
            if(tripSchedule.isPresent()) {
                return TripScheduleMapper.toTripScheduleDto(tripSchedule.get());
            } else {
                if(createSchedForTrip) {
                    TripSchedule tripSchedule1 = new TripSchedule();
                    tripSchedule1.setDetail(trip.get());
                    tripSchedule1.setTripDate(tripDate);
                    tripSchedule1.setAvailableSeats(trip.get().getBus().getCapacity());
                    return TripScheduleMapper.toTripScheduleDto(tripScheduleRepository.save(tripSchedule1));
                } else {
                    exceptionWithId(EntityType.TRIP, ExceptionType.ENTITY_NOT_FOUND, 2L, tripDto.getId().toString(),
                            tripDate.toString());
                }
            }
        }
        throw exception(EntityType.TRIP, ExceptionType.ENTITY_NOT_FOUND, tripDto.getId().toString());
    }

    /**
     * Method to book ticket for a given trip schedule
     *
     * @param tripScheduleDto
     * @param userDto
     * @return
     */
    @Override
    public TicketDto bookTicket(TripScheduleDto tripScheduleDto, UserDto userDto) {
        UserAccount userAccount = getUser(userDto.getEmail());
        if(userAccount != null) {
            Optional<TripSchedule> tripSchedule = tripScheduleRepository.findById(tripScheduleDto.getId());
            if(tripSchedule.isPresent()) {
                Ticket ticket = new Ticket();
                ticket.setCancellable(false);
                ticket.setJourneyDate(tripSchedule.get().getTripDate());
                ticket.setPassenger(userAccount);
                ticket.setTripSchedule(tripSchedule.get());
                ticket.setSeatNumber(tripSchedule.get().getDetail().getBus()
                        .getCapacity() - tripSchedule.get().getAvailableSeats());
                ticketRepository.save(ticket);
                tripSchedule.get().setAvailableSeats(tripSchedule.get().getAvailableSeats() - 1);
                tripScheduleRepository.save(tripSchedule.get());
                return TicketMapper.toTicketDto(ticket);
            }
            throw exceptionWithId(EntityType.TRIP, ExceptionType.ENTITY_NOT_FOUND, 2L, tripScheduleDto.getTripId(),
                    tripScheduleDto.getTripDate().toString());
        }
        throw exception(EntityType.USER, ExceptionType.ENTITY_NOT_FOUND, userDto.getEmail());
    }

    /**
     * Returns a new RuntimeException
     *
     * @param entityType
     * @param exceptionType
     * @param args
     * @return
     */
    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return BRSException.throwException(entityType, exceptionType, args);
    }


    /**
     * Returns a new RuntimeException
     *
     * @param entityType
     * @param exceptionType
     * @param args
     * @return
     */
    private RuntimeException exceptionWithId(EntityType entityType, ExceptionType exceptionType, Long id,
                                             String... args) {
        return BRSException.throwException(entityType, exceptionType, id, args);
    }


    /**
     * Fetch user from UserDto
     *
     * @param email
     * @return
     */
    private UserAccount getUser(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Fetch Agency from agencyCode
     *
     * @param agencyCode
     * @return
     */
    private Agency getAgency(String agencyCode) {
        return agencyRepository.findByCode(agencyCode);
    }

    /**
     * Fetch Stop from stopCode
     *
     * @param stopCode
     * @return
     */
    private Stop getStop(String stopCode) {
        return stopRepository.findByCode(stopCode);
    }

    /**
     * Fetch Bus from busCode, since it is unique we don't have issues of finding duplicate Buses
     *
     * @param busCode
     * @return
     */
    private Bus getBus(String busCode) {
        return busRepository.findByCode(busCode);
    }


    /**
     * Search for all Trips between src and dest stops
     *
     * @param sourceStopCode
     * @param destinationStopCode
     * @return
     */
    private List<Trip> findTripsBetweenStops(String sourceStopCode, String destinationStopCode) {
        Optional<Stop> sourceStop = Optional.ofNullable(stopRepository.findByCode(sourceStopCode));
        if(sourceStop.isPresent()) {
            Optional<Stop> destStop= Optional.ofNullable(stopRepository.findByCode(destinationStopCode));
            if(destStop.isPresent()) {
                List<Trip> availableTrips = tripRepository
                        .findAllBySourceStopAndDestStop(sourceStop.get(), destStop.get());
                if(!availableTrips.isEmpty()) {
                    return availableTrips;
                }
                return Collections.emptyList();
            }
            throw exception(EntityType.STOP, ExceptionType.ENTITY_NOT_FOUND, destinationStopCode);
        }
        throw exception(EntityType.STOP, ExceptionType.ENTITY_NOT_FOUND, sourceStopCode);
    }

}
