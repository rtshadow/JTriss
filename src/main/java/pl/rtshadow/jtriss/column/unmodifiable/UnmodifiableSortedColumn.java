package pl.rtshadow.jtriss.column.unmodifiable;

import static pl.rtshadow.jtriss.utils.BinarySearch.lowerBound;
import static pl.rtshadow.jtriss.utils.BinarySearch.upperBound;

import java.util.Iterator;
import java.util.List;

import pl.rtshadow.jtriss.column.SortedColumn;
import pl.rtshadow.jtriss.column.element.ColumnElement;
import pl.rtshadow.jtriss.column.element.StandardColumnElement;
import pl.rtshadow.jtriss.common.ValueRange;

public class UnmodifiableSortedColumn<T extends Comparable<? super T>> implements SortedColumn<T> {
  private final List<ColumnElement<T>> elements;
  private final int id;

  UnmodifiableSortedColumn(List<ColumnElement<T>> elements, int id) {
    this.elements = elements;
    this.id = id;
  }

  @Override
  public SortedColumn<T> getSubColumn(ValueRange<T> range) {
    int leftIndex = 0;
    int rightIndex = elements.size() - 1;

    if (range.isFiniteOnTheLeft()) {
      leftIndex = lowerBound(elements, new StandardColumnElement<T>(range.getLeft()), range.isOpenOnTheLeft());
    }
    if (range.isFiniteOnTheRight()) {
      rightIndex = upperBound(elements, new StandardColumnElement<T>(range.getRight()), range.isOpenOnTheRight());
    }

    return new UnmodifiableSortedColumn<T>(elements.subList(leftIndex, rightIndex + 1), id);
  }

  @Override
  public Iterator<ColumnElement<T>> iterator() {
    return elements.iterator();
  }

  @Override
  public boolean contains(ColumnElement<T> element) {
    if (elements.isEmpty()) {
      return false;
    }

    return element.getColumnId() == id
        && positionOf(0) <= element.getPositionInColumn()
        && element.getPositionInColumn() <= positionOf(elements.size() - 1);
  }

  private int positionOf(int index) {
    return elements.get(index).getPositionInColumn();
  }

  @Override
  public int getSize() {
    return elements.size();
  }

  @Override
  public int getId() {
    return id;
  }
}
