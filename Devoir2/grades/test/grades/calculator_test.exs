defmodule Grades.CalculatorTest do
  use ExUnit.Case
  alias Grades.Calculator

  describe "percentage_grade/1" do
    test "test1" do
      assert 38 ==
               Calculator.percentage_grade(%{
                 homework: [],
                 labs: [],
                 midterm: 0.75,
                 final: 0.75
               })
    end
  end

  describe "percentage_grade/2" do
    test "test2" do
      assert 75 ==
               Calculator.percentage_grade(%{
                 homework: [0.5, 0.9],
                 labs: [0.8, 0.9],
                 midterm: 0.84,
                 final: 0.67
               })
    end
  end

  describe "letter_grade/1" do
    test "test3" do
      assert "EIN" ==
               Calculator.letter_grade(%{
                 homework: [],
                 labs: [],
                 midterm: 0.75,
                 final: 0.90
               })
    end
  end

  describe "letter_grade/2" do
    test "test4" do
      assert "EIN" ==
               Calculator.letter_grade(%{
                 homework: [0.5],
                 labs: [0.2],
                 midterm: 0.96,
                 final: 0.84
               })
    end
  end

  describe "letter_grade/3" do
    test "test5" do
      assert "A+" ==
               Calculator.letter_grade(%{
                 homework: [0.9,0.9,0.92],
                 labs: [0.87,0.88,0.95,0.96],
                 midterm: 0.92,
                 final: 0.96
               })
    end
  end

  describe "letter_grade/4" do
    test "test6" do
      assert "A" ==
               Calculator.letter_grade(%{
                 homework: [0.85,0.85,0.86],
                 labs: [0.87,0.88,0.88,0.86],
                 midterm: 0.89,
                 final: 0.90
               })
    end
  end

  describe "letter_grade/5" do
    test "test7" do
      assert "A-" ==
               Calculator.letter_grade(%{
                 homework: [0.80,0.80,0.81],
                 labs: [0.82,0.83,0.84,0.80],
                 midterm:  0.82,
                 final: 0.85
               })
    end
  end

  describe "letter_grade/6" do
    test "test8" do
      assert "B+" ==
               Calculator.letter_grade(%{
                 homework: [0.75,0.75,0.76],
                 labs: [0.77,0.78,0.78,0.76],
                 midterm:  0.79,
                 final: 0.80
               })
    end
  end


  describe "letter_grade/7" do
    test "test9" do
      assert "B" ==
               Calculator.letter_grade(%{
                 homework: [0.70,0.70,0.71],
                 labs: [0.72,0.73,0.74,0.70],
                 midterm:  0.72,
                 final: 0.75
               })
    end
  end

  describe "letter_grade/8" do
    test "test10" do
      assert "C+" ==
               Calculator.letter_grade(%{
                 homework: [0.65,0.65,0.66],
                 labs: [0.67,0.68,0.68,0.66],
                 midterm: 0.69,
                 final: 0.70
               })
    end
  end

  describe "letter_grade/9" do
    test "test11" do
      assert "C" ==
               Calculator.letter_grade(%{
                 homework: [0.60,0.60,0.61],
                 labs: [0.62,0.63,0.64,0.60],
                 midterm: 0.62,
                 final: 0.65
               })
    end
  end

  describe "letter_grade/10" do
    test "test12" do
      assert "D+" ==
               Calculator.letter_grade(%{
                 homework: [0.55,0.55,0.56],
                 labs: [0.57,0.58,0.58,0.56],
                 midterm: 0.59,
                 final: 0.60
               })
    end
  end

  describe "letter_grade/11" do
    test "test13" do
      assert "D" ==
               Calculator.letter_grade(%{
                 homework: [0.50,0.50,0.51],
                 labs: [0.52,0.53,0.54,0.50],
                 midterm: 0.52,
                 final: 0.55
               })
    end
  end

  describe "letter_grade/12" do
    test "test14" do
      assert "E" ==
               Calculator.letter_grade(%{
                 homework: [0.45,0.45,0.46],
                 labs: [0.47,0.48,0.48,0.46],
                 midterm: 0.49,
                 final: 0.45
               })
    end
  end

  describe "letter_grade/13" do
    test "test15" do
      assert "F" ==
               Calculator.letter_grade(%{
                 homework: [0.40,0.40,0.40],
                 labs: [0.26,0.28,0.23,0.25,0.29],
                 midterm: 0.41,
                 final: 0.40
               })
    end
  end

  describe "numeric_grade/1" do
    test "test16" do
      assert 0 ==
               Calculator.numeric_grade(%{
                 homework: [],
                 labs: [],
                 midterm: 0.75,
                 final: 0.90
               })
    end
  end

  describe "numeric_grade/2" do
    test "test17" do
      assert 0 ==
               Calculator.numeric_grade(%{
                 homework: [0.5],
                 labs: [0.2],
                 midterm: 0.96,
                 final: 0.84
               })
    end
  end

  describe "numeric_grade/3" do
    test "test18" do
      assert 10 ==
               Calculator.numeric_grade(%{
                 homework: [0.9,0.9,0.92],
                 labs: [0.87,0.88,0.95,0.96],
                 midterm: 0.92,
                 final: 0.96
               })
    end
  end

  describe "numeric_grade/4" do
    test "test19" do
      assert 9 ==
               Calculator.numeric_grade(%{
                 homework: [0.85,0.85,0.86],
                 labs: [0.87,0.88,0.88,0.86],
                 midterm: 0.89,
                 final: 0.90
               })
    end
  end

  describe "numeric_grade/5" do
    test "test20" do
      assert 8 ==
               Calculator.numeric_grade(%{
                 homework: [0.80,0.80,0.81],
                 labs: [0.82,0.83,0.84,0.80],
                 midterm:  0.82,
                 final: 0.85
               })
    end
  end

  describe "numeric_grade/6" do
    test "test21" do
      assert 7 ==
               Calculator.numeric_grade(%{
                 homework: [0.75,0.75,0.76],
                 labs: [0.77,0.78,0.78,0.76],
                 midterm:  0.79,
                 final: 0.80
               })
    end
  end

  describe "numeric_grade/7" do
    test "test22" do
      assert 6 ==
               Calculator.numeric_grade(%{
                 homework: [0.70,0.70,0.71],
                 labs: [0.72,0.73,0.74,0.70],
                 midterm:  0.72,
                 final: 0.75
               })
    end
  end

  describe "numeric_grade/8" do
    test "test23" do
      assert 5 ==
               Calculator.numeric_grade(%{
                 homework: [0.65,0.65,0.66],
                 labs: [0.67,0.68,0.68,0.66],
                 midterm: 0.69,
                 final: 0.70
               })
    end
  end

  describe "numeric_grade/9" do
    test "test24" do
      assert 4 ==
               Calculator.numeric_grade(%{
                 homework: [0.60,0.60,0.61],
                 labs: [0.62,0.63,0.64,0.60],
                 midterm: 0.62,
                 final: 0.65
               })
    end
  end

  describe "numeric_grade/10" do
    test "test25" do
      assert 3 ==
               Calculator.numeric_grade(%{
                 homework: [0.55,0.55,0.56],
                 labs: [0.57,0.58,0.58,0.56],
                 midterm: 0.59,
                 final: 0.60
               })
    end
  end

  describe "numeric_grade/11" do
    test "test26" do
      assert 2 ==
               Calculator.numeric_grade(%{
                 homework: [0.50,0.50,0.51],
                 labs: [0.52,0.53,0.54,0.50],
                 midterm: 0.52,
                 final: 0.55
               })
    end
  end

  describe "numeric_grade/12" do
    test "test27" do
      assert 1 ==
               Calculator.numeric_grade(%{
                 homework: [0.45,0.45,0.46],
                 labs: [0.47,0.48,0.48,0.46],
                 midterm: 0.49,
                 final: 0.45
               })
    end
  end

  describe "numeric_grade/13" do
    test "test28" do
      assert 0 ==
               Calculator.numeric_grade(%{
                 homework: [0.40,0.40,0.40],
                 labs: [0.26,0.28,0.23,0.25,0.29],
                 midterm: 0.41,
                 final: 0.40
               })
    end
  end


end
