package com.ugurhmz.bookstore.controller;


import com.ugurhmz.bookstore.dto.requestDto.PostCodeRequestDto;
import com.ugurhmz.bookstore.dto.responseDto.PostCodeResponseDto;
import com.ugurhmz.bookstore.service.PostCodeService;
import jdk.jshell.spi.ExecutionControl;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/postcode")
public class PostCodeController {

    private final PostCodeService postCodeService;

    @Autowired
    public PostCodeController(PostCodeService postCodeService) {
        this.postCodeService = postCodeService;
    }

    // CREATE
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createPostCode(@RequestBody final PostCodeRequestDto postCodeRequestDto) {
        try {
            PostCodeResponseDto postCodeResponseDto = postCodeService.createPostCode(postCodeRequestDto);

            return new ResponseEntity<>(Collections.singletonMap("message",postCodeResponseDto), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(Collections.singletonMap("error",e.getMessage()), HttpStatus.NOT_FOUND);
        }
         catch (Exception e) {
            String errMsg = "Error while creating PostCode " + e.getMessage();
            return new ResponseEntity<>(Collections.singletonMap("error", errMsg), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET ONE
    @GetMapping("/{postCodeId}")
    public ResponseEntity<Map<String, Object>> getPostCodeWithId(@PathVariable  Long postCodeId){
      try {
        PostCodeResponseDto postCodeResponseDto =   postCodeService.getPostCode(postCodeId);
        return new ResponseEntity<>(Collections.singletonMap("message", postCodeResponseDto),HttpStatus.OK);
      } catch (IllegalArgumentException e) {
        return new ResponseEntity<>(Collections.singletonMap("error",e.getMessage()), HttpStatus.NOT_FOUND);
      }
      catch (Exception e) {
          String errMsg = "Error while getting postCode: " + e.getMessage();
          return new ResponseEntity<>(Collections.singletonMap("error:",errMsg), HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    // UPDATE
    @PutMapping("/update/{postCodeId}")
    public ResponseEntity<Map<String, Object>> updatePostCode(@PathVariable  Long postCodeId,
                                                              @RequestBody final PostCodeRequestDto postCodeRequestDto )
    {
        try {
            PostCodeResponseDto postCodeResponseDto = postCodeService.updatePostCode(postCodeId, postCodeRequestDto);
            return new ResponseEntity<>(Collections.singletonMap("message", postCodeResponseDto), HttpStatus.OK);
        } catch(IllegalArgumentException e) {
            return new ResponseEntity<>(Collections.singletonMap("error", e.getMessage()),HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            String errMsg = "Error while updating postCode";
            return new ResponseEntity<>(Collections.singletonMap("error", errMsg), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    // DELETE
    @DeleteMapping("/delete/{postCodeId}")
    public ResponseEntity<Map<String, String>> deletePostCode(@PathVariable Long postCodeId

    ) {
        try {
           String deleteMsg =  postCodeService.deletePostCode(postCodeId);
           return new ResponseEntity<>(Collections.singletonMap("message", deleteMsg),HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(Collections.singletonMap("error", e.getMessage()),HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            String errMSG = "Error while deleting postcode!!";
            return new ResponseEntity<>(Collections.singletonMap("error", errMSG),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
