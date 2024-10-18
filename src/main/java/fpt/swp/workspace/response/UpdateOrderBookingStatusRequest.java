package fpt.swp.workspace.response;

import fpt.swp.workspace.models.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateOrderBookingStatusRequest {
    private BookingStatus orderStatus;
}
