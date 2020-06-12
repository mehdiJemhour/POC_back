package com.jemhourmehdi.importbdx.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BordereauData{
    private List<Map> columns;
    private List<Map> rows;
}
