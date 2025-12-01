defmodule Day06Test do
  use ExUnit.Case
  import Day06

  defp read_input do
    {:ok, contents} = File.read("input/day06.txt")

    contents
    |> String.split("\n")
  end

  test "use case" do
    assert 1000 =
             grid_points({:on, 0, 0, 999, 0})
             |> Enum.count()
  end

  test "part 1" do
    assert 569_999 = read_input() |> solve_par1()
  end

  test "part 2" do
    assert 17_836_115 = read_input() |> solve_part2()
  end
end
