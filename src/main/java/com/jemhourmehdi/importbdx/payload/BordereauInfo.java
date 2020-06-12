package com.jemhourmehdi.importbdx.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class BordereauInfo{
    private String mga;
    private String paAgreement;
    private String program;
    private String reportingMonth;
    private String reportingYear;
    private String receivedDate;
}
