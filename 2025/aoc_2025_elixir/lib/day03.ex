defmodule Day03 do
  def largest(num, count, word, acc) do
    # parts has only 1 item if no match
    parts = String.split(word, "#{num}", parts: 2)

    cond do
      Enum.count(acc) >= count ->
        acc |> Enum.reverse() |> Enum.join() |> String.to_integer()

      Enum.count(parts) > 1 && Enum.count(acc) + String.length(Enum.at(parts, 1)) >= count - 1 ->
        largest(9, count, Enum.at(parts, 1), [num | acc])

      true ->
        largest(num - 1, count, word, acc)
    end
  end

  def part1(battery_pack) do
    battery_pack
    |> Enum.map(fn pack ->
      largest(9, 2, pack, [])
    end)
    |> Enum.sum()
  end

  def part2(battery_pack) do
    battery_pack
    |> Enum.map(fn pack ->
      largest(9, 12, pack, [])
    end)
    |> Enum.sum()
  end
end
