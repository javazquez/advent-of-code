defmodule Day08 do
  def in_memory_string(string) do
    string
    # strip off the end quotes first
    |> String.replace(~r/^\"/, "")
    |> String.replace(~r/\"$/, "")
    |> String.replace(~r/\\\"/, "_")
    |> String.replace(~r/\\\\/, "_")
    |> String.replace(~r/\\x[0-9a-fA-F][0-9a-fA-F]/, "_")
    |> String.length()
  end

  def expand_string(string) do
    string
    |> String.replace(~r/\"/, "---")
    |> String.replace(~r/\\\\/, "----")
    |> String.replace(~r/\\x[0-9a-fA-F][0-9a-fA-F]/, "-----")
    |> String.length()
  end

  def solve_part1(lines) do
    code = Enum.map(lines, &String.length/1) |> Enum.sum()
    memory = Enum.map(lines, &in_memory_string/1) |> Enum.sum()
    code - memory
  end

  def solve_part2(lines) do
    code = Enum.map(lines, &String.length/1) |> Enum.sum()
    memory = Enum.map(lines, &expand_string/1) |> Enum.sum()
    memory - code
  end
end
