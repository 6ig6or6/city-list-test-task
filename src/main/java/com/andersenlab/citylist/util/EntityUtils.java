package com.andersenlab.citylist.util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.FeatureDescriptor;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class EntityUtils {
    /**
     * Copies non-null properties from the source object to the destination object,
     * ignoring any fields listed in the excludedFields parameter.
     *
     * @param source      the source object to copy properties from
     * @param destination the destination object to copy properties to
     * @param exclude     the names of any fields to ignore during the copy
     */
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
