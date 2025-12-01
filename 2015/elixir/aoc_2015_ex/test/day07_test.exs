defmodule Day07Test do
  use ExUnit.Case
  import Day07

  defp read_input do
    {:ok, contents} = File.read("input/day07.txt")

    contents
    |> String.split("\n")
  end

  test "parsing strings" do
    assert %{"u" => {:or, "t", "s"}} = create_map("t OR s -> u")
    assert %{"a" => "lx"} = create_map("lx -> a")
    assert %{"ay" => {:not, "ax"}} = create_map("NOT ax -> ay")
    assert %{"hf" => {:rshift, "he", "2"}} = create_map("he RSHIFT 2 -> hf")
    assert %{"lr" => {:or, "lf", "lq"}} = create_map("lf OR lq -> lr")
    assert %{"lu" => {:and, "lr", "lt"}} = create_map("lr AND lt -> lu")
    assert %{"ek" => {:or, "dy", "ej"}} = create_map("dy OR ej -> ek")
    assert %{"cy" => {:and, "1", "cx"}} = create_map("1 AND cx -> cy")
    assert %{"hv" => {:lshift, "hb", "1"}} = create_map("hb LSHIFT 1 -> hv ")
  end

  @tag timeout: :infinity
  test "part 1" do
    46065 = read_input() |> solve_part1()
  end

  test "part 2" do
    14134 = read_input() |> solve_part2()
  end
end
