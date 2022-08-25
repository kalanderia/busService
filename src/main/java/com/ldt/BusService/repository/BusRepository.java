package com.ldt.BusService.repository;
import com.ldt.BusService.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface BusRepository extends JpaRepository<Bus,Integer>{
    Bus findById(int busId);
    @Query(value = "select * from bus INTO OUTFILE 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Files\\details.csv'", nativeQuery = true)
    List<Bus> findAll();
    @Query(value="select * from bus where (case when '0'=?3 then name is NOT NULL else name =?3 end)"+
            "AND(case when '0'=?4 then type is NOT NULL else type =?4 end)"+
            "AND(case when '0' =?5 then number is NOT NULL else number =?5 end)"+
            "AND(case when '0' =?6 then bus_reservation_status is NOT NULL else bus_reservation_status =?6 end) LIMIT ?1,?2", nativeQuery=true)
    List<Bus> getBusByCustom(int pageLimit, int offSet,String name,String type,String number,String busReservationStatus);

    @Query(value="select count(*) from bus where (case when '0'=?1 then name is NOT NULL else name =?1 end)"+
            "AND(case when '0'=?2 then type is NOT NULL else type =?2 end)"+
            "AND(case when '0' =?3 then number is NOT NULL else number =?3 end)"+
            "AND(case when '0' =?4 then bus_reservation_status is NOT NULL else bus_reservation_status =?4 end)", nativeQuery=true)
    int getFilterByCount (String name,String type,String number,String busReservationStatus);

    @Query(value = "select * from bus INTO OUTFILE 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Files\\details.csv'", nativeQuery = true)
    List<Bus> getData();
}
