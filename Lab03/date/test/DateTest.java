import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DateTest {

  @Test
  void nextDate_tc01() {
    Date today = new Date(1700, 6, 20);
    Date expectedTomorrow = new Date(1700, 6, 21);
    assertEquals(expectedTomorrow, today.nextDate());
  }

  @Test
  void nextDate_tc02() {
    Date today = new Date(2005, 4, 15);
    Date expectedTomorrow = new Date(2005, 4, 16);
    assertEquals(expectedTomorrow, today.nextDate());
  }

  @Test
  void nextDate_tc03() {
    Date today = new Date(1901, 7, 20);
    Date expectedTomorrow = new Date(1901, 7, 21);
    assertEquals(expectedTomorrow, today.nextDate());
  }

  @Test
  void nextDate_tc04() {
    Date today = new Date(3456, 3, 27);
    Date expectedTomorrow = new Date(3456, 3, 28);
    assertEquals(expectedTomorrow, today.nextDate());
  }

  @Test
  void nextDate_tc05() {
    Date today = new Date(1500, 2, 17);
    Date expectedTomorrow = new Date(1500, 2, 18);
    assertEquals(expectedTomorrow, today.nextDate());
  }

  @Test
  void nextDate_tc06() {
    Date today = new Date(1700, 6, 29);
    Date expectedTomorrow = new Date(1700, 6, 30);
    assertEquals(expectedTomorrow, today.nextDate());
  }

  @Test
  void nextDate_tc07() {
    Date today = new Date(1800, 11, 29);
    Date expectedTomorrow = new Date(1800, 11, 30);
    assertEquals(expectedTomorrow, today.nextDate());
  }

  @Test
  void nextDate_tc08() {
    Date today = new Date(3453, 1, 29);
    Date expectedTomorrow = new Date(3453, 1, 30);
    assertEquals(expectedTomorrow, today.nextDate());
  }

  @Test
  void nextDate_tc09() {
    Date today = new Date(444, 2, 29);
    Date expectedTomorrow = new Date(444, 3, 1);
    assertEquals(expectedTomorrow, today.nextDate());
  }

  @Test
  void nextDate_tc10() {
    Date today = new Date(2005, 4, 30);
    Date expectedTomorrow = new Date(2005, 5, 1);
    assertEquals(expectedTomorrow, today.nextDate());
  }

  @Test
  void nextDate_tc11() {
    Date today = new Date(3453, 1, 30);
    Date expectedTomorrow = new Date(3453, 1, 31);
    assertEquals(expectedTomorrow, today.nextDate());
  }

  @Test
  void nextDate_tc12() {
    Date today = new Date(3456, 3, 30);
    Date expectedTomorrow = new Date(3456, 3, 31);
    assertEquals(expectedTomorrow, today.nextDate());
  }

  @Test
  void nextDate_tc13() {
    Date today = new Date(1901, 7, 31);
    Date expectedTomorrow = new Date(1901, 8, 1);
    assertEquals(expectedTomorrow, today.nextDate());
  }

  @Test
  void nextDate_tc14() {
    Date today = new Date(3453, 1, 31);
    Date expectedTomorrow = new Date(3453, 2, 1);
    assertEquals(expectedTomorrow, today.nextDate());
  }

  @Test
  void nextDate_tc15() {
    Date today = new Date(3456, 12, 31);
    Date expectedTomorrow = new Date(3457, 1, 1);
    assertEquals(expectedTomorrow, today.nextDate());
  }

  @Test
  void nextDate_invalid_tc16() {
    assertThrows(
      IllegalArgumentException.class,
      () -> new Date(1500, 2, 31)
    );
  }

  @Test
  void nextDate_invalid_tc17() {
    assertThrows(
      IllegalArgumentException.class,
      () -> new Date(1500, 2, 29)
    );
  }

  @Test
  void nextDate_invalid_tc18() {
    assertThrows(
      IllegalArgumentException.class,
      () -> new Date(-1, 10, 20)
    );
  }

  @Test
  void nextDate_invalid_tc19() {
    assertThrows(
      IllegalArgumentException.class,
      () -> new Date(1458, 15, 12)
    );
  }

  @Test
  void nextDate_invalid_tc20() {
    assertThrows(
      IllegalArgumentException.class,
      () -> new Date(1975, 6, -50)
    );
  }

  //Le code en dessous son les tests ajouter pour le lab

  @Test
    void toString_valid(){
        Date date = new Date(2020, 5, 25);
        assertEquals("2020/May/25", date.toString());
    }

    @Test
    void nextDate_with9MonthAnd29Day() {
      Date today = new Date(1800, 9, 29);
      Date expectedTomorrow = new Date(1800, 9, 30);
      assertEquals(expectedTomorrow, today.nextDate());
  }

  @Test
    void nextDate_leapYearAnd29day() {
      Date today = new Date(1920, 2, 29);
      Date expectedTomorrow = new Date(1920, 3, 1);
      assertEquals(expectedTomorrow, today.nextDate());
  }

  @Test
    void nextDate_leapYearAndNot29day() {
      Date today = new Date(1920, 2, 28);
      Date expectedTomorrow = new Date(1920, 2, 29);
      assertEquals(expectedTomorrow, today.nextDate());
  }

  @Test
    void nextDate_notLeapYearAnd28day() {
      Date today = new Date(1919, 2, 28);
      Date expectedTomorrow = new Date(1919, 3, 1);
      assertEquals(expectedTomorrow, today.nextDate());
  }

  @Test
    void nextDate_notLeapYearAndNot28day() {
      Date today = new Date(1919, 03, 28);
      Date expectedTomorrow = new Date(1919, 03, 29);
      assertEquals(expectedTomorrow, today.nextDate());
  }

  @Test
    void isLeapYear_divisibleBy400() {
      Date today = new Date(2000, 01, 05);
      assertEquals(true, today.isLeapYear());
  }

  @Test
    void equals_isNotADate() {
       Date today = new Date(1999, 06, 02);
      Object obj = new Object();
      assertEquals(false, today.equals(obj));
  }

  @Test
    void equals() {
      Date today = new Date(2013, 7, 2);
      Date alsoToday = new Date(2013, 7, 2);
      assertEquals(true, today.equals(alsoToday));
  }

  @Test
    void equals_notSameYear() {
      Date today = new Date(2013, 7, 2);
      Date alsoToday = new Date(2012, 7, 2);
      assertEquals(false, today.equals(alsoToday));
  }

  @Test
    void equals_notSameMonth() {
      Date today = new Date(2013, 7, 2);
      Date alsoToday = new Date(2013, 8, 2);
      assertEquals(false, today.equals(alsoToday));
  }

  @Test
    void equals_notSameDay() {
      Date today = new Date(2013, 7, 2);
      Date alsoToday = new Date(2013, 7, 3);
      assertEquals(false, today.equals(alsoToday));
  }

}