package com.example.dc.controller;

import com.example.dc.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author irfan.nagoo
 */

@RestController
@RequestMapping("/distributed-computing")
@Slf4j
public class DistributedComputingController {

    /**
     * Sample API to create HTTP session for cluster replication use case.
     *
     * <br><br><u>Reference:</u> https://tomcat.apache.org/tomcat-9.0-doc/cluster-howto.html
     *
     * @param request - HTTP Request
     * @return Response with status
     */
    @PostMapping("/session/create")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public BaseResponse createSession(HttpServletRequest request) {
        log.info("Start creating session");
        if (request.getSession(false) == null) {
            log.info("Creating session");
            HttpSession session = request.getSession();
            session.setAttribute("mode", "replication");
            return new BaseResponse(HttpStatus.CREATED.name(), String.format("Session Successfully Created with Id[%s]",
                    session.getId()));
        } else {
            String id = request.getSession().getId();
            log.info("Session already created with Id[{}]", id);
            return new BaseResponse(HttpStatus.OK.name(), String.format("Session Already Exists with Id[%s]", id));
        }
    }

    /**
     * Sample API to invalidate the session used within cluster for replication use case
     *
     * @param request - HTTP request
     * @return Response with status
     */
    @DeleteMapping("/session/invalidate")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public BaseResponse invalidateSession(HttpServletRequest request) {
        log.info("Start invalidating session");
        if (request.getSession(false) == null) {
            log.info("Session does not exist");
            return new BaseResponse(HttpStatus.NOT_FOUND.name(), "Session does not Exist");
        } else {
            request.getSession().invalidate();
            log.info("Session destroyed ");
            return new BaseResponse(HttpStatus.OK.name(), "Session Invalidated Successfully");
        }
    }


}
