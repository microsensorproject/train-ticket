package admintravel.controller;

import admintravel.entity.TravelInfo;
import admintravel.service.AdminTravelService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.*;

/**
 * @author fdse
 */
@RestController
@RequestMapping("/api/v1/admintravelservice")
public class AdminTravelController {
    @Autowired
    AdminTravelService adminTravelService;

    private static final Logger logger = LoggerFactory.getLogger(AdminTravelController.class);

    @GetMapping(path = "/welcome")
    public String home(@RequestHeader HttpHeaders headers) {
        return "Welcome to [ AdminTravel Service ] !";
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/admintravel")
    public HttpEntity getAllTravels(@RequestHeader HttpHeaders headers) {
        logger.info("Get all travels");
        return ok(adminTravelService.getAllTravels(headers));
    }

    @PostMapping(value = "/admintravel")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "request", value = "TravelInfo",dataType = "TravelInfo", paramType = "body",required = true),
            @ApiImplicitParam(name = "headers",  paramType = "header",required = true)
    })
    public HttpEntity addTravel(@RequestBody TravelInfo request, @RequestHeader HttpHeaders headers) {
        logger.info("Add travel, trip id: {}, train type id: {}, form station {} to station {}, login id: {}",
                request.getTripId(), request.getTrainTypeId(), request.getStartingStationId(), request.getStationsId(), request.getLoginId());
        return ok(adminTravelService.addTravel(request, headers));
    }

    @PutMapping(value = "/admintravel")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "request", value = "TravelInfo",dataType = "TravelInfo", paramType = "body",required = true),
            @ApiImplicitParam(name = "headers",  paramType = "header",required = true)
    })
    public HttpEntity updateTravel(@RequestBody TravelInfo request, @RequestHeader HttpHeaders headers) {
        logger.info("Update travel, trip id: {}, train type id: {}, form station {} to station {}, login id: {}",
                request.getTripId(), request.getTrainTypeId(), request.getStartingStationId(), request.getStationsId(), request.getLoginId());
        return ok(adminTravelService.updateTravel(request, headers));
    }

    @DeleteMapping(value = "/admintravel/{tripId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tripId", value = "tripId",dataType = "String", paramType = "path",required = true,defaultValue = "G1234"),
            @ApiImplicitParam(name = "headers",  paramType = "header",required = true)
    })
    public HttpEntity deleteTravel(@PathVariable String tripId, @RequestHeader HttpHeaders headers) {
        logger.info("Delete travel: trip id: {}", tripId);
        return ok(adminTravelService.deleteTravel(tripId, headers));
    }

}
