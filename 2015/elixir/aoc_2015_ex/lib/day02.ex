defmodule Day02 do
  def calc_area([{l, _}, {w, _}, {h, _}]) do
    smallest = Enum.reduce([l * w, w * h, h * l], &min/2)
    surface_area = 2 * l * w + 2 * w * h + 2 * h * l
    surface_area + smallest
  end

  def calc_ribbon([{l, _}, {w, _}, {h, _}]) do
    [a, b, _] = Enum.sort([l, w, h])
    ribbon = a + a + b + b
    bow = l * w * h
    ribbon + bow
  end

  def part1_answer(lst) do
    Enum.map(lst, &calc_area/1)
    |> Enum.sum()
  end

  def part2_answer(input) do
    Enum.map(input, &calc_ribbon/1)
    |> Enum.sum()
  end
end
