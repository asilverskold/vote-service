package ru.example.java.demo.convertor;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.convention.NameTokenizers;
import org.modelmapper.convention.NamingConventions;
import org.springframework.stereotype.Component;
import ru.example.java.demo.dto.DishDto;
import ru.example.java.demo.dto.MenuDto;
import ru.example.java.demo.model.Dish;
import ru.example.java.demo.model.Menu;

@Component
@RequiredArgsConstructor
public class MenuConvertor {
    private final ModelMapper modelMapper;

    public MenuConvertor() {
        this.modelMapper = new ModelMapper();
        modelMapper.createTypeMap(Dish.class, DishDto.class);
        Configuration configuration = modelMapper.getConfiguration();
        configuration.setFieldAccessLevel(Configuration.AccessLevel.PUBLIC);
        configuration.setSourceNamingConvention(NamingConventions.JAVABEANS_ACCESSOR);
        configuration.setDestinationNamingConvention(NamingConventions.JAVABEANS_MUTATOR);
        configuration.setSourceNameTokenizer(NameTokenizers.CAMEL_CASE);
        configuration.setDestinationNameTokenizer(NameTokenizers.CAMEL_CASE);
        configuration.setMatchingStrategy(MatchingStrategies.STANDARD);
    }

    public MenuDto convertToDto(Menu menu) {
        return modelMapper.map(menu, MenuDto.class);
    }

}
