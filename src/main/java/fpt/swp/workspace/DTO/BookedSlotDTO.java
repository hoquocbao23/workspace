package fpt.swp.workspace.DTO;

import lombok.Data;

import java.util.ArrayList;
import java.util.Map;

@Data
public class BookedSlotDTO {
    private Map<String, ArrayList<Integer>> bookedSlots;

}
