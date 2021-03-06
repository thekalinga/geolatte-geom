package org.geolatte.geom.codec.db.oracle;

import org.geolatte.geom.*;
import org.geolatte.geom.crs.CoordinateReferenceSystem;
import org.geolatte.geom.crs.CoordinateReferenceSystems;
import org.geolatte.geom.crs.LinearUnit;
import org.junit.Test;

import static org.geolatte.geom.builder.DSL.*;
import static org.geolatte.geom.codec.db.oracle.SDOGeometryHelper.sdoGeometry;
import static org.junit.Assert.assertEquals;

/**
 * Created by Karel Maesen, Geovise BVBA on 01/04/15.
 */
public class TestSdoPointEncoder {

    final CoordinateReferenceSystem<G2D> wgs84 = CoordinateReferenceSystems.WGS84;
    final CoordinateReferenceSystem<G3D> wgs84z = CoordinateReferenceSystems.addVerticalSystem(wgs84, G3D.class,
            LinearUnit.METER);
    final CoordinateReferenceSystem<G3DM> wgs84zm = CoordinateReferenceSystems.addLinearSystem(wgs84z, G3DM.class,
            LinearUnit.METER);

    @Test
    public void test2DMPointNullCrs() {
        Point<C2DM> point = point(CoordinateReferenceSystems.PROJECTED_2DM_METER, cM(12, 14, 3));
        SDOGeometry expected = sdoGeometry(3301, -1, null, new int[]{1, 1, 1}, new Double[]{12d, 14d, 3d});
        assertEquals(expected, Encoders.encode(point));
    }


    @Test
    public void test3DPointWGS() {
        Point<G3D> point = point(wgs84z, g(12, 14, 3));
        SDOGeometry expected = sdoGeometry(3001, 4326, null, new int[]{1, 1, 1}, new Double[]{12d, 14d, 3d});
        assertEquals(expected, Encoders.encode(point));
    }

    @Test
    public void test3DMPointWGS() {
        Point<G3DM> point = point(wgs84zm, g(12, 14, 3, 6));
        SDOGeometry expected = sdoGeometry(4401, 4326, null, new int[]{1, 1, 1}, new Double[]{12d, 14d, 3d, 6d});
        assertEquals(expected, Encoders.encode(point));
    }

}
