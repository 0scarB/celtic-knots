package pattern;

import org.junit.Test;
import static org.junit.Assert.*;
import pattern.exception.NonRectangularGridException;

public class RectangularKnotTest {

  @Test
  public void RectangularKnot_initalization() {
    short[][] grid = {{}};
    boolean cornerIsOpen = false;
    boolean overlapStartsBottomLeftToTopRight = true;
    RectangularKnot knot = new RectangularKnot(
      grid,
      cornerIsOpen,
      overlapStartsBottomLeftToTopRight
    );

    assertArrayEquals(grid, knot.grid);
    assertSame(cornerIsOpen, knot.cornerIsOpen);
    assertSame(
      overlapStartsBottomLeftToTopRight,
      knot.overlapStartsBottomLeftToTopRight
    );
  }

  @Test
  public void RectangularKnot_correctDefaults() {
    RectangularKnot knot1 = new RectangularKnot(new short[][]{{}});
    RectangularKnot knot2 = new RectangularKnot(new short[][]{{}}, false);

    assertTrue(knot1.cornerIsOpen);
    assertTrue(knot1.overlapStartsBottomLeftToTopRight);
    assertFalse(knot2.cornerIsOpen);
    assertTrue(knot2.overlapStartsBottomLeftToTopRight);
  }

  @Test(expected = NonRectangularGridException.class)
  public void RectangularKnot_throwsExceptionOnNonRectangularTileReferenceGrid() {
    new RectangularKnot(new short[][]{
      {1, 2, 3, 4},
      {1, 2, 3, 4, 5}
    });
  }
}
