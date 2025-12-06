defmodule Day06 do
  # {[123, 328, 51, 64], "*"}
  def apply_operator({nums, operator}) do
    cond do
      operator == "*" -> Enum.product(nums)
      operator == "+" -> Enum.sum(nums)
    end
  end

  def format_prob1(contents) do
    temp =
      contents
      |> String.split("\n")
      |> Enum.map(fn line ->
        line
        |> String.trim()
        |> String.split(~r/\s+/)
      end)

    nums =
      temp
      |> Enum.slice(0..(Enum.count(temp) - 2))
      |> Enum.map(fn lst -> lst |> Enum.map(&String.to_integer/1) end)
      |> Enum.zip()
      |> Enum.map(&Tuple.to_list/1)

    operators = List.last(temp)
    Enum.zip(nums, operators)
  end

  # using whitespace as a placeholder in the list for ziping numbers that do
  # not extend the full column.  [{["1"], [" "], [" "]},{["2"], ["4"], [" "]}...]
  # 1234
  #  45
  #   6
  def format_prob2(contents) do
    temp =
      contents
      |> String.split("\n")
      |> Enum.map(fn line ->
        Regex.scan(~r/./, line)
      end)

    nums =
      temp
      |> Enum.slice(0..(Enum.count(temp) - 2))
      |> Enum.zip()
      # ex format [{["1"], ["3"], [""]},{["2"],["4"],[""]}
      |> Enum.map(fn x -> x |> Tuple.to_list() |> Enum.join() |> String.trim() end)
      |> Enum.chunk_by(fn x -> x == "" end)
      |> Enum.filter(fn x -> x != [""] end)

    operators =
      List.last(temp)
      |> List.flatten()
      |> Enum.filter(fn x -> x != " " end)

    Enum.zip(nums, operators)
  end

  def part1(lines) do
    lines
    |> Enum.map(&apply_operator/1)
    |> Enum.sum()
  end

  def part2(lines) do
    lines
    |> Enum.map(fn {num, op} -> {Enum.map(num, &String.to_integer/1), op} end)
    |> Enum.map(&apply_operator/1)
    |> Enum.sum()
  end
end
