package com.example.Restaurante.services;

import com.example.Restaurante.dtos.MenuResponseDTO;
import com.example.Restaurante.entities.Menu;
import com.example.Restaurante.maps.MenuMap;
import com.example.Restaurante.repositories.MenuRepository;
import com.example.Restaurante.validations.MenuValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MenuService {
    @Autowired
    MenuValidation validation;
    @Autowired
    MenuRepository menuRepository;
    @Autowired
    MenuMap map;

    public MenuResponseDTO createMenu(Menu registerMenuData)throws Exception{
        try {
            if(!registerMenuData.getRole().equals('A')){
                throw new Exception("El rol no tiene permisos para crear un plato");
            }if(validation.validatePrice(registerMenuData.getPrice())){
                throw new Exception("Precio Invalido");
            }if(validation.LocalValidate(registerMenuData.getLocal())){
                throw new Exception("Debe agregar una sede");
            }

            return map.TransformMenu(menuRepository.save(registerMenuData));

    }catch(Exception error){
        throw new Exception(error.getMessage());
        }
    }

    public MenuResponseDTO updateInfo(Menu editMenuData, Integer idMenu) throws Exception {
        try {

            if (editMenuData.getRole() != ('A')) {
                throw new Exception("El ROL no esta autorizado para registrar un plato");
            }

            Optional<Menu> MenuOptional = menuRepository.findById(idMenu);
            if (MenuOptional.isEmpty()) { //si el plato no existe en BD
                throw new Exception("El plato no existe");
            }

            Menu platoExistente = MenuOptional.get();

            if (editMenuData.getPrice() != null) {
                platoExistente.setPrice(editMenuData.getPrice());
            }
            if (editMenuData.getLocal() != null) {
                platoExistente.setLocal(editMenuData.getLocal());
            }
            if (editMenuData.getDescription() != null) {
                platoExistente.setDescription(editMenuData.getDescription());
            }

            return map.TransformMenu(menuRepository.save(platoExistente));
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    public MenuResponseDTO updateStatus(Menu editMenuData,Integer idMenuEdit)throws Exception{try {
        if(!editMenuData.getRole().equals('A')){
            throw new Exception("El ROL no esta autorizado para registrar un plato");
        }

        Optional<Menu> menuOptional=menuRepository.findById(idMenuEdit);
        if(!menuOptional.isPresent()){
            throw new Exception("El plato no existe");
        }

        Menu menuFound=menuOptional.get();
        if (editMenuData.getState() != null) {
            menuFound.setState(editMenuData.getState());
        }
        Menu menuUpdate=menuRepository.save(menuFound);

        return  map.TransformMenu(menuRepository.save(menuUpdate));


    }catch(Exception error){
        throw new Exception(error.getMessage());
    }


}


public Page<MenuResponseDTO> obtainMenuLocalCategory(String category, String local,Integer registernumbers) throws Exception{
        try{

            Pageable Pagerli= PageRequest.of(0, registernumbers);

            Page<Menu> menuPagerFinded=menuRepository.findByCategoryAndLocal(category,local,Pagerli);

            return menuPagerFinded.map(Menu -> map.TransformMenu(Menu));

        }catch(Exception error){
            throw new Exception(error.getMessage());
        }
}

}
