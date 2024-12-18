package fpt.swp.workspace.repository;

import fpt.swp.workspace.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RoomRepository extends JpaRepository<Room, String> {

    @Query("SELECT r FROM Room r WHERE r.building.buildingId = ?1  ORDER BY r.creationTime desc ")
    List<Room> getRoomByBuilding( String buildingId );

    @Query("SELECT r FROM Room r WHERE r.building.buildingId = ?1 AND r.status = ?2 ORDER BY r.creationTime desc")
    List<Room> getRoomByBuildingAndStatus( String buildingId, String status);


    @Query("SELECT r FROM Room r WHERE r.building.buildingId = ?1 AND r.roomType.id = ?2 ORDER BY r.creationTime desc")
    List<Room> getRoomsByBuildingAndRoomType(String building, String roomType);

    @Query("SELECT r FROM Room r WHERE r.roomType.id= ?1 ORDER BY r.creationTime desc")
    List<Room> getRoomByRoomType(String roomTypeId);

    @Query("SELECT r FROM Room r WHERE r.roomType.roomTypeName= ?1 ORDER BY r.creationTime desc")
    List<Room> getRoomsByRoomType(String roomTypeName);

    @Query("SELECT r.roomId FROM Room r where r.roomId = ?1 ORDER BY r.creationTime desc ")
    boolean getRoomIdByRoomId(int roomId);


    // ---- DASHBOARD ----
    @Query("SELECT COUNT(r) FROM Room r WHERE r.building.buildingId = ?1")
    int getTotalSpace(String buildingId);

    @Query("SELECT COUNT(r) FROM Room r ")
    int getTotalSpace();

    @Query("SELECT r FROM Room r WHERE r.building.buildingId = ?1 ")
    List<Room> getByRoomType(String buildingId);
}
