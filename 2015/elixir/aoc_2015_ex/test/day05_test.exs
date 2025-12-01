defmodule Day05Test do
  use ExUnit.Case
  import Day05

  defp read_input do
    {:ok, contents} = File.read("input/day05.txt")

    contents
    |> String.split("\n")
  end

  test "part 1" do
    assert 1 = part1_answer(["ugknbfddgicrmopn"])
    assert 1 = part1_answer(["aaa"])
    assert 0 = part1_answer(["jchzalrnumimnmhp"])
    assert 0 = part1_answer(["haegwjzuvuyypxyu"])
    assert 0 = part1_answer(["dvszwmarrgswjxmb"])
    assert 255 = part1_answer(read_input())
    assert 55 = part2_answer(read_input())
  end
end
