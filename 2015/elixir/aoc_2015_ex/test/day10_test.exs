defmodule Day10Test do
  use ExUnit.Case
  import Day10

  test "part 1" do
    329_356 = solve_part1("3113322113", 40)
  end

  test "part 2" do
    4_666_278 = solve_part1("3113322113", 50)
  end
end
