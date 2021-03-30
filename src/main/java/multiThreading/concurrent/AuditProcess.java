package multiThreading.concurrent;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;


public class AuditProcess {


    private String TRLATDSC = "";


    private String TSTEPOPRNAME = "";


    private String TSTEPOPRINTRCODETYPE = "";


    private String TSTEPOPRINTRCODE = "";


    private String TSTEPOPRAFFLCTN = "";


    private String TOPRAFFLCTNINTRCODE = "";


    private String TSTEPOPRTIME = "";


    private String TSTEPOPROP = "";

    @JsonProperty(value = "TRLATDSC")
    public String getTRLATDSC() {
        return TRLATDSC;
    }

    @JsonProperty(value = "TRLATDSC")
    public void setTRLATDSC(String TRLATDSC) {
        this.TRLATDSC = TRLATDSC;
    }

    @JsonProperty(value = "TSTEPOPRNAME")
    public String getTSTEPOPRNAME() {
        return TSTEPOPRNAME;
    }

    @JsonProperty(value = "TSTEPOPRNAME")
    public void setTSTEPOPRNAME(String TSTEPOPRNAME) {
        this.TSTEPOPRNAME = TSTEPOPRNAME;
    }

    @JsonProperty(value = "TSTEPOPRINTRCODETYPE")
    public String getTSTEPOPRINTRCODETYPE() {
        return TSTEPOPRINTRCODETYPE;
    }

    @JsonProperty(value = "TSTEPOPRINTRCODETYPE")
    public void setTSTEPOPRINTRCODETYPE(String TSTEPOPRINTRCODETYPE) {
        this.TSTEPOPRINTRCODETYPE = TSTEPOPRINTRCODETYPE;
    }

    @JsonProperty(value = "TSTEPOPRINTRCODE")
    public String getTSTEPOPRINTRCODE() {
        return TSTEPOPRINTRCODE;
    }

    @JsonProperty(value = "TSTEPOPRINTRCODE")
    public void setTSTEPOPRINTRCODE(String TSTEPOPRINTRCODE) {
        this.TSTEPOPRINTRCODE = TSTEPOPRINTRCODE;
    }

    @JsonProperty(value = "TSTEPOPRAFFLCTN")
    public String getTSTEPOPRAFFLCTN() {
        return TSTEPOPRAFFLCTN;
    }

    @JsonProperty(value = "TSTEPOPRAFFLCTN")
    public void setTSTEPOPRAFFLCTN(String TSTEPOPRAFFLCTN) {
        this.TSTEPOPRAFFLCTN = TSTEPOPRAFFLCTN;
    }

    @JsonProperty(value = "TOPRAFFLCTNINTRCODE")
    public String getTOPRAFFLCTNINTRCODE() {
        return TOPRAFFLCTNINTRCODE;
    }

    @JsonProperty(value = "TOPRAFFLCTNINTRCODE")
    public void setTOPRAFFLCTNINTRCODE(String TOPRAFFLCTNINTRCODE) {
        this.TOPRAFFLCTNINTRCODE = TOPRAFFLCTNINTRCODE;
    }

    @JsonProperty(value = "TSTEPOPRTIME")
    public String getTSTEPOPRTIME() {
        return TSTEPOPRTIME;
    }

    @JsonProperty(value = "TSTEPOPRTIME")
    public void setTSTEPOPRTIME(String TSTEPOPRTIME) {
        this.TSTEPOPRTIME = TSTEPOPRTIME;
    }

    @JsonProperty(value = "TSTEPOPROP")
    public String getTSTEPOPROP() {
        return TSTEPOPROP;
    }

    @JsonProperty(value = "TSTEPOPROP")
    public void setTSTEPOPROP(String TSTEPOPROP) {
        this.TSTEPOPROP = TSTEPOPROP;
    }
}
