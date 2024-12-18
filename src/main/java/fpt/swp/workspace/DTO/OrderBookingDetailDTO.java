package fpt.swp.workspace.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import fpt.swp.workspace.models.*;
import lombok.Data;


import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class OrderBookingDetailDTO {

        private String bookingId;
        private String roomId;
        private List<TimeSlot> slots;
        private String customerId;
        private String createAt;
        private String checkinDate;
        private String checkoutDate;
        private float totalPrice;
        private float discount;
        private String note;
        private BookingStatus status;
        private Map<String, Integer> serviceItems;


    }
