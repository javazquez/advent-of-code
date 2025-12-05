defmodule Day05 do
  def find_overlaps([min1, max1], [min2, max2]) do
    cond do
      min2 >= min1 && min2 <= max1 && max2 <= max1 ->
        [min1, max1]

      min2 >= min1 && min2 <= max1 && max2 > max1 ->
        [min1, max2]

      min2 < min1 && max2 < max1 ->
        [min2, max1]

      min2 < min1 && max2 > max1 ->
        [min2, max2]

      true ->
        []
    end
  end

  # function assumes sorted list
  def create_overlaps([lhs | []], acc) do
    [lhs | acc]
  end

  def create_overlaps([lhs, rhs | t], acc) do
    v = find_overlaps(lhs, rhs)

    if v == [] do
      create_overlaps([rhs | t], [lhs | acc])
    else
      create_overlaps([v | t], acc)
    end
  end

  def part1([min_max_list, ids]) do
    ids
    |> Enum.filter(fn id ->
      min_max_list |> Enum.filter(fn [min, max] -> id >= min && id <= max end) != []
    end)
    |> Enum.count()
  end

  def part2([min_max_list, _ids]) do
    min_max_list
    |> Enum.sort(fn [x1, _y1], [x2, _y2] -> x1 <= x2 end)
    |> create_overlaps([])
    |> Enum.map(fn [x, y] -> y - x + 1 end)
    |> Enum.sum()
  end
end
