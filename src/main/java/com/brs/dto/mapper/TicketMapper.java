package com.brs.dto.mapper;

/*@Mapper
public interface TicketMapper {
    TicketDto toTicketDto(Ticket ticket);
    List<TicketDto> toTicketDtos(List<Ticket> tickets);
    Ticket toTicket(TicketDto ticketDto);
}*/

import com.brs.dto.model.bus.TicketDto;
import com.brs.model.bus.Ticket;

public class TicketMapper {

    public static TicketDto toTicketDto(Ticket ticket) {

        TicketDto ticketDto = new TicketDto();
        ticketDto.setId(ticket.getId());
        ticketDto.setBusCode(ticket.getTripSchedule().getDetail().getBus().getCode());
        ticketDto.setSeatNumber(ticket.getSeatNumber());
        ticketDto.setSourceStop(ticket.getTripSchedule().getDetail().getSourceStop());
        ticketDto.setDestinationStop(ticket.getTripSchedule().getDetail().getDestStop());
        ticketDto.setCancellable(false);
        ticketDto.setJourneyDate(ticket.getJourneyDate());
        ticketDto.setPassengerName(ticket.getPassenger().getFirstName() + " " + ticket.getPassenger().getLastName());
        ticketDto.setPassengerMobileNumber(ticket.getPassenger().getMobileNumber());

        return ticketDto;
    }
}
