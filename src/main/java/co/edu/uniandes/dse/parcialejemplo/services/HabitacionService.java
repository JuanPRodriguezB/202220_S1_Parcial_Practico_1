package co.edu.uniandes.dse.parcialejemplo.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcialejemplo.entities.HabitacionEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialejemplo.repositories.HabitacionRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HabitacionService {

    @Autowired
    HabitacionRepository habitacionRepository;

    @Transactional
    public HabitacionEntity createHotel(HabitacionEntity habitacionEntity) throws IllegalOperationException
    {
        log.info("Inicia la creacion de una habitacion");
        if (!habitacionEntity.getBanios() <= habitacionEntity.getPersonas()){
            throw new IllegalOperationException("El numero de personas debe ser mayor o igual al numero de banios");
        }

        log.info("Termina proceso de creacion de hotel");
        return habitacionRepository.save(habitacionEntity);
    }
}
