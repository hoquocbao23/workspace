package fpt.swp.workspace.controller;


import fpt.swp.workspace.models.Manager;
import fpt.swp.workspace.response.APIResponse;
import fpt.swp.workspace.response.ManagerRequest;
import fpt.swp.workspace.response.ResponseHandler;
import fpt.swp.workspace.service.ManagerService;
import fpt.swp.workspace.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/managers")
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    @Autowired
    private StaffService staffService;

//    @PostMapping()
//    public ResponseEntity<APIResponse<Manager>> createManager(@RequestBody ManagerRequest request) {
//        try {
//            Manager createManager = managerService.createManager(request);
//            APIResponse<Manager> response = new APIResponse<>("Manager create successfully", createManager);
//            return ResponseEntity.status(HttpStatus.OK).body(response);
//        } catch (RuntimeException e) {
//            APIResponse<Manager> response = new APIResponse<>(e.getMessage(), null);
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
//        }
//    }

//    @GetMapping
//    public Page<Manager> getAllManagers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
//        return managerService.getAllManagers(page, size);
//    }

    @GetMapping
    public ResponseEntity<Object> getAllManagers() {
        try{
            return ResponseHandler.responseBuilder("Ok", HttpStatus.OK, managerService.getAllManagers());
        }catch (RuntimeException e){
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }



    @GetMapping("/{id}")
    public Manager getManagerById(@PathVariable String id) {
        return managerService.getManagerById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Manager>> updateManager(@PathVariable String id,@RequestBody ManagerRequest request) {
        try {
            Manager updateManager = managerService.updateManager(id, request);
            APIResponse<Manager> response = new APIResponse<>("Manager updated successfully", updateManager);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (RuntimeException e) {
            APIResponse<Manager> response = new APIResponse<>(e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<Object> getManagerProfile(@RequestHeader("Authorization") String token){
        String jwt = token.substring(7);
        try{
            return ResponseHandler.responseBuilder("Ok", HttpStatus.OK, managerService.getManagerProfile(jwt));
        }catch (NullPointerException e){
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<APIResponse<Void>> deleteManager(@PathVariable String id) {
//        try {
//            managerService.deleteManager(id);
//            APIResponse<Void> response = new APIResponse<>("Manager deleted successfully", null);
//            return ResponseEntity.status(HttpStatus.OK).body(response);
//        } catch (RuntimeException e) {
//            APIResponse<Void> response = new APIResponse<>(e.getMessage(), null);
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
//        }
//    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<Object> updateManagerStatus(@PathVariable("id") String id) {
        try{
            managerService.updateManageStatus(id);
            return ResponseHandler.responseBuilder("Cập nhập thành công", HttpStatus.OK);
        }catch (RuntimeException e ) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("get-staff-work-shift/{id}")
    public ResponseEntity<Object> getWorkShift(@PathVariable String id) {
        try{
            return ResponseHandler.responseBuilder("Ok", HttpStatus.OK, staffService.getWorkShiftByStaffId(id));
        }catch (Exception e){
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
