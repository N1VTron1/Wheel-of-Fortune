package model;

import java.util.HashMap;
import java.util.Map;

public class Wedge {

  public enum WedgeType {
    MONEY,
    BANKRUPT,
    LOSE_TURN
  }

  private final WedgeType wedgeType;

  private static final Map<WedgeType, String> WEDGE_TYPE_STRING_MAP;

  static {
    WEDGE_TYPE_STRING_MAP = new HashMap<>();
    WEDGE_TYPE_STRING_MAP.put(WedgeType.BANKRUPT, "BANKRUPT");
    WEDGE_TYPE_STRING_MAP.put(WedgeType.LOSE_TURN, "LOSE A TURN");
  }

  private int value = 0;

  public Wedge(int value) {
    this.wedgeType = WedgeType.MONEY;
    this.value = value;
  }

  public Wedge(WedgeType wedgeType) {
    this.wedgeType = wedgeType;
    this.value = 0;
  }

  @Override
  public String toString() {
    switch (wedgeType) {
      case MONEY:
        return "$" + this.value;
      default:
        return Wedge.WEDGE_TYPE_STRING_MAP.get(wedgeType);
    }
  }

  public WedgeType getWedgeType() {
    return wedgeType;
  }

  public int getValue() {
    return value;
  }
}
