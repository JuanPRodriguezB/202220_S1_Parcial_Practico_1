package co.edu.uniandes.dse.parcialejemplo.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcialejemplo.entities.HabitacionEntity;
import co.edu.uniandes.dse.parcialejemplo.entities.HotelEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialejemplo.repositories.HabitacionRepository;
import co.edu.uniandes.dse.parcialejemplo.repositories.HotelRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HotelHabitacionService {

    @Autowired
    HabitacionRepository habitacionRepository;

    @Autowired
    HotelRepository hotelRepository;

    /**
	 * Agregar un book a la editorial
	 *
	 * @param habitacionId
	 * @param hotelId 
	 * @return
	 * @throws EntityNotFoundException 
	 */
    @Transactional
    public HabitacionEntity addHabitacion(Long habitacionId, Long hotelId) throws EntityNotFoundException
    {
        log.info("Inicia el proceso de agregarle una habitacion a un hotel");
        Optional<HotelEntity> hotelEntity = hotelRepository.findById(hotelId);
		if(hotelEntity.isEmpty())
			throw new EntityNotFoundException("HOTEL_NOT_FOUND");

        Optional<HabitacionEntity> habitacionEntity = habitacionRepository.findById(habitacionId);
        if(habitacionEntity.isEmpty())
            throw new EntityNotFoundException("ROOM_NOT_FOUND");
        
            habitacionEntity.get().setHotel(hotelEntity.get());
        log.info("Termina proceso de creacion de habitacion");
        return habitacionEntity.get();
    }
}
