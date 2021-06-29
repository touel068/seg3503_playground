defmodule FlipFlopTest do
  use ExUnit.Case
  doctest Flipflop

describe "flipflop/1" do
  test "test1" do
    assert 21 ==
      Flipflop.flipflop(%{
               a:1,
              b:2
             })
  end
end

describe "flipflop/2" do
  test "test2" do
    assert 47 ==
      Flipflop.flipflop(%{
               a:7,
              b:4
             })
  end
end

describe "flipflop/3" do
  test "test3" do
    assert "flip" ==
      Flipflop.flipflop(%{
               a:3,
              b:1
             })
  end
end

describe "flipflop/4" do
  test "test4" do
    assert "flop" ==
      Flipflop.flipflop(%{
               a:1,
              b:5
             })
  end
end

describe "flipflop/5" do
  test "test5" do
    assert "flipflop" ==
      Flipflop.flipflop(%{
               a:3,
              b:5
             })
  end
end

describe "flipflop/6" do
  test "test6" do
    assert "flopflip" ==
      Flipflop.flipflop(%{
               a:5,
              b:3
             })
  end
end

describe "flipflop/7" do
  test "test7" do
    assert "flopflip" ==
      Flipflop.flipflop(%{
               a:10,
              b:9
             })
  end
end
