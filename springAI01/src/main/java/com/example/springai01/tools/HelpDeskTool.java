package com.example.springai01.tools;


import com.example.springai01.entity.HelpDeskTicket;
import com.example.springai01.model.TicketRequest;
import com.example.springai01.service.HelpDeksTicketService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.model.ToolContext;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class HelpDeskTool {

    private static final Logger logger = LoggerFactory.getLogger(HelpDeskTool.class);

    private final HelpDeksTicketService helpDeksTicketService;
     //returnDirect = true
     // for returning the result directly

    @Tool(name = "createTicket", description = "Create a support ticket")
    String createTIcket(@ToolParam(description = "Details to create a support tickert")
                        TicketRequest ticketRequest, ToolContext toolContext

                        ){
        String username = toolContext.getContext().get("username").toString();
        logger.info("Creating a ticket for user {}", toolContext.getContext().get("username"));
        HelpDeskTicket savedTicket = helpDeksTicketService.createTicket(ticketRequest, username);
        logger.info("Created a ticket for user and ticket id {}", savedTicket.getId());
        return  "Ticker # " + savedTicket.getId() + " created successfully for user " + savedTicket.getUsername() + " at " + savedTicket.getCreatedAt();

    }


    @Tool(description = "Fetch the status of the  tickets based on a given username")
    List<HelpDeskTicket> getTicketStatus(ToolContext toolContext) {
        String username = toolContext.getContext().get("username").toString();
        logger.info("Fetching the  tickets for user {}", toolContext.getContext().get("username"));
        List<HelpDeskTicket> tickets = helpDeksTicketService.getTicketsByUsername(username);
        logger.info("Found {}  tickets for user {}", tickets.size(), toolContext.getContext().get("username"));
        return tickets;
    }
}
