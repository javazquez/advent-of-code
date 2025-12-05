defmodule Day04 do
  def rolls_adjecent({x, y, _}, grid \\ MapSet.new()) do
    adjs = [
      {x - 1, y, "@"},
      {x + 1, y, "@"},
      {x + 1, y + 1, "@"},
      {x + 1, y - 1, "@"},
      {x - 1, y + 1, "@"},
      {x - 1, y - 1, "@"},
      {x, y + 1, "@"},
      {x, y - 1, "@"}
    ]

    adjs |> Enum.filter(fn x -> MapSet.member?(grid, x) end) |> Enum.count()
  end

  def create_gridline(row, s) do
    s |> String.graphemes() |> Enum.with_index(fn element, index -> {row, index, element} end)
  end

  def create_grid(lines) do
    lines |> Enum.with_index(fn line, index -> create_gridline(index, line) end)
  end

  def remove_tp(grid, acc) do
    options = grid |> Enum.filter(fn {_, _, x} -> x == "@" end)

    tp_remove =
      options |> Enum.filter(fn opt -> rolls_adjecent(opt, MapSet.new(options)) < 4 end)

    rolls_removed = Enum.count(tp_remove)
    newg = options -- tp_remove

    if(rolls_removed == 0) do
      acc
    else
      remove_tp(newg, rolls_removed + acc)
    end
  end

  def part1(lines) do
    grid =
      lines
      |> create_grid()
      |> List.flatten()

    options = grid |> Enum.filter(fn {_, _, x} -> x == "@" end)

    options
    |> Enum.filter(fn opt -> rolls_adjecent(opt, MapSet.new(grid)) < 4 end)
    |> Enum.count()
  end

  def part2(lines) do
    lines
    |> create_grid()
    |> List.flatten()
    |> remove_tp(0)
  end
end
