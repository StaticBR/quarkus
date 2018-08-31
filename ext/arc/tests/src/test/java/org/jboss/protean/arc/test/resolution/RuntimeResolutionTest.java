package org.jboss.protean.arc.test.resolution;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.AbstractList;
import java.util.List;

import javax.enterprise.util.TypeLiteral;
import javax.inject.Singleton;

import org.jboss.protean.arc.Arc;
import org.jboss.protean.arc.ArcContainer;
import org.jboss.protean.arc.InstanceHandle;
import org.jboss.protean.arc.test.ArcTestContainer;
import org.junit.Rule;
import org.junit.Test;

public class RuntimeResolutionTest {

    @Rule
    public ArcTestContainer container = new ArcTestContainer(MyList.class);

    @SuppressWarnings("serial")
    @Test
    public void testResolution() throws IOException {
        ArcContainer arc = Arc.container();
        // MyList bean types: MyList, AbstractList<Integer>, List<Integer>, AbstractCollection<Integer>, Iterable<Integer>, Object
        InstanceHandle<List<? extends Number>> list = arc.instance(new TypeLiteral<List<? extends Number>>() {
        });
        assertTrue(list.isAvailable());
        assertEquals(Integer.valueOf(7), list.get().get(1));
    }

    @Singleton
    static class MyList extends AbstractList<Integer> {

        @Override
        public Integer get(int index) {
            return Integer.valueOf(7);
        }

        @Override
        public int size() {
            return 0;
        }

    }

}
