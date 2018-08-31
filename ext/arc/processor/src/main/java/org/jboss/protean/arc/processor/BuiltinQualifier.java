package org.jboss.protean.arc.processor;

import java.util.Collections;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Default;

import org.jboss.jandex.AnnotationInstance;
import org.jboss.protean.gizmo.BytecodeCreator;
import org.jboss.protean.gizmo.FieldDescriptor;
import org.jboss.protean.gizmo.ResultHandle;

enum BuiltinQualifier {

    DEFAULT(AnnotationInstance.create(DotNames.DEFAULT, null, Collections.emptyList()),
            Default.Literal.class.getName()), ANY(AnnotationInstance.create(DotNames.ANY, null, Collections.emptyList()), Any.Literal.class.getName()),;

    private final AnnotationInstance instance;

    private final String literalType;

    private BuiltinQualifier(AnnotationInstance instance, String literalType) {
        this.instance = instance;
        this.literalType = literalType;
    }

    AnnotationInstance getInstance() {
        return instance;
    }

    ResultHandle getLiteralInstance(BytecodeCreator creator) {
        return creator.readStaticField(FieldDescriptor.of(literalType, "INSTANCE", literalType));
    }

    static BuiltinQualifier of(AnnotationInstance instance) {
        for (BuiltinQualifier qualifier : values()) {
            if (qualifier.getInstance().name().equals(instance.name())) {
                return qualifier;
            }
        }
        return null;
    }

}
