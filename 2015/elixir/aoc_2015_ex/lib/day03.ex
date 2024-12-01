defmodule Day03 do

  def move(?^, [{x,y} | tail]) do
    [{x,y+1}, {x,y} | tail]
  end

  def move(?>, [{x,y} | tail]) do
    [{x+1,y}, {x,y} | tail]
  end

  def move(?v, [{x,y} | tail]) do
    [{x,y-1}, {x,y} | tail]
  end

  def move(?<, [{x,y} | tail]) do
    [{x-1,y}, {x,y} | tail]
  end

  def deliver_to_houses([], coord) do
    coord
  end

  def deliver_to_houses([h|t], coord) do
      deliver_to_houses(t, move(h, coord))
  end

  def part1_answer(lst) do
    deliver_to_houses(lst, [{0,0}])
    |> Enum.uniq()
    |> Enum.count()
  end

  def part2_answer(instructions) do
    santa = Enum.drop_every(instructions, 2)
    robot = Enum.take_every(instructions, 2)
    Enum.concat(deliver_to_houses(santa, [{0,0}]),
                deliver_to_houses(robot, [{0,0}]))
    |> Enum.uniq()
    |> Enum.count()
  end
end
