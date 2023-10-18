package Main;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackerRepository extends JpaRepository<Tracker,Integer> {

    @Override
    List<Tracker> findAll();

}
