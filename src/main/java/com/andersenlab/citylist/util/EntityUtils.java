package com.andersenlab.citylist.util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.FeatureDescriptor;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class EntityUtils {

    public static void patchEntity(final Object source, final Object destination, final String... exclude) {
        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
        final List<String> ignoredFields = new ArrayList<>(Stream.of(wrappedSource.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null)
                .toList());
        ignoredFields.addAll(List.of(exclude));
        BeanUtils.copyProperties(source, destination, ignoredFields.toArray(String[]::new));
    }

}
