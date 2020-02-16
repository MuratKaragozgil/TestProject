package com.shopping.cart.controller;

import com.shopping.cart.model.dto.CampaignDTO;
import com.shopping.cart.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Murat Karagözgil
 */
@Controller
@RequestMapping("campaign")
public class CampaignController extends BaseController {

    @Autowired
    private CampaignService campaignService;

    @GetMapping("get/{campaignId}")
    public ResponseEntity<CampaignDTO> getCampaignById(HttpServletRequest request, @PathVariable("campaignId") Long campaignId) {
        logUserAction(request, "GetCampaignById::{}", campaignId);

        CampaignDTO campaignDTO = campaignService.getCampaignById(campaignId);

        return ResponseEntity.ok(campaignDTO);
    }

    @PostMapping("save")
    public ResponseEntity<CampaignDTO> saveCampaign(HttpServletRequest request, @RequestBody CampaignDTO campaignDTO) {
        logUserAction(request, "SaveCampaignDto::{}", campaignDTO);

        CampaignDTO savedCampaign = campaignService.saveCampaign(campaignDTO);

        return ResponseEntity.ok(savedCampaign);
    }
}
