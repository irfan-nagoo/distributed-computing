package com.example.dc.controller;

import com.example.dc.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URI;

import static com.example.dc.constants.MessagingConstants.*;

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
     * <br><u>Reference:</u>
     * <a href="https://tomcat.apache.org/tomcat-9.0-doc/cluster-howto.html">Apache Tomcat 9.0</a>
     *
     * @param request - HTTP Request
     * @return Response with status
     */
    @PostMapping("/session/create")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<BaseResponse> createSession(HttpServletRequest request) {
        log.info("Start creating session");
        if (request.getSession(false) == null) {
            log.info("Creating session");
            HttpSession session = request.getSession();
            session.setAttribute("mode", "replication");
            return ResponseEntity.created(URI.create("uri")).body(new BaseResponse(HttpStatus.CREATED.name(),
                    String.format(SUCCESS_MSG, session.getId())));
        } else {
            String id = request.getSession().getId();
            log.info("Session already created with Id[{}]", id);
            return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.name(), String.format(ALREADY_EXISTS_MSG, id)));
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
    public ResponseEntity<BaseResponse> invalidateSession(HttpServletRequest request) {
        log.info("Start invalidating session");
        if (request.getSession(false) == null) {
            log.info("Session does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new BaseResponse(HttpStatus.NOT_FOUND.name(), NOT_EXISTS_MSG));
        } else {
            request.getSession().invalidate();
            log.info("Session destroyed ");
            return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.name(), INVALIDATED_MSG));
        }
    }


}
