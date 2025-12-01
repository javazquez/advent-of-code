defmodule Day11Test do
  use ExUnit.Case
  import Day11

  test "examples" do
    "abcdffaa" = solve_part1("abcdefgh")
  end

  test "part1" do
    "hxbxxyzz" = solve_part1("hxbxwxba")
  end

  test "part2" do
    "hxcaabcc" = solve_part2("hxbxwxba")
  end
end
