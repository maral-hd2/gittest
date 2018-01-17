/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2004, by Object Refinery Limited and Contributors.
 *
 * Project Info:  http://www.jfree.org/jfreechart/index.html
 *
 * This library is free software; you can redistribute it and/or modify it under the terms
 * of the GNU Lesser General Public License as published by the Free Software Foundation;
 * either version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this
 * library; if not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA 02111-1307, USA.
 *
 * [Java is a trademark or registered trademark of Sun Microsystems, Inc. 
 * in the United States and other countries.]
 *
 * ---------------------------------
 * RendererCategoryPackageTests.java
 * ---------------------------------
 * (C) Copyright 2004, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * $Id: RendererCategoryPackageTests.java,v 1.1 2004/08/31 14:48:28 mungady Exp $
 *
 * Changes:
 * --------
 * 23-Aug-2004 : Restructured org.jfree.chart.renderer package (DG);
 *
 */

package org.jfree.chart.renderer.category.junit;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * A collection of tests for the org.jfree.chart.renderer.category package.
 * <P>
 * These tests can be run using JUnit (http://www.junit.org).
 */
public class RendererCategoryPackageTests extends TestCase {

    /**
     * Returns a test suite to the JUnit test runner.
     *
     * @return The test suite.
     */
    public static Test suite() {
        TestSuite suite = new TestSuite("org.jfree.chart.renderer");
        suite.addTestSuite(AbstractCategoryItemRendererTests.class);
        suite.addTestSuite(AreaRendererTests.class);
        suite.addTestSuite(BarRendererTests.class);
        suite.addTestSuite(BarRenderer3DTests.class);
        suite.addTestSuite(BoxAndWhiskerRendererTests.class);
        suite.addTestSuite(DefaultCategoryItemRendererTests.class);
        suite.addTestSuite(GanttRendererTests.class);
        suite.addTestSuite(GroupedStackedBarRendererTests.class);
        suite.addTestSuite(IntervalBarRendererTests.class);
        suite.addTestSuite(LayeredBarRendererTests.class);
        suite.addTestSuite(LineAndShapeRendererTests.class);
        suite.addTestSuite(MinMaxCategoryRendererTests.class);
        suite.addTestSuite(StackedAreaRendererTests.class);
        suite.addTestSuite(StackedBarRendererTests.class);
        suite.addTestSuite(StackedBarRenderer3DTests.class);
        suite.addTestSuite(StatisticalBarRendererTests.class);
        suite.addTestSuite(WaterfallBarRendererTests.class);
        return suite;
    }

    /**
     * Constructs the test suite.
     *
     * @param name  the suite name.
     */
    public RendererCategoryPackageTests(String name) {
        super(name);
    }

}
