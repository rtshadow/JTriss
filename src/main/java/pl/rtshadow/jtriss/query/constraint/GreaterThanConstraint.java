package pl.rtshadow.jtriss.query.constraint;

import static pl.rtshadow.jtriss.common.ValueRange.leftFiniteRange;
import pl.rtshadow.jtriss.common.ValueRange;

public class GreaterThanConstraint<T extends Comparable<? super T>> implements Constraint<T> {
  private final ValueRange<T> range;

  public GreaterThanConstraint(T value) {
    range = leftFiniteRange(value).openOnTheLeft();
  }

  public static <T extends Comparable<? super T>> Constraint<T> greaterThan(T value) {
    return new GreaterThanConstraint<T>(value);
  }

  @Override
  public ValueRange<T> reduceToRange() {
    return range;
  }

}
