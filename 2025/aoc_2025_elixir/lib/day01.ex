defmodule Day01 do
  def convert_instructions(s) do
    [[direction, n]] = Regex.scan(~r/(L|R)(\d+)/, s, capture: :all_but_first)

    if direction == "L" do
      {:left, String.to_integer(n)}
    else
      {:right, String.to_integer(n)}
    end
  end

  def parse_input(lines) do
    lines
    |> Enum.map(fn l ->
      convert_instructions(l)
    end)
  end

  def rotate({:right, distance}, current_spot) do
    dail_right = Stream.cycle(0..99 |> Enum.to_list())

    account_for_zero_enum_take = if current_spot == 0, do: 1, else: current_spot

    zeroes =
      dail_right
      |> Stream.drop(account_for_zero_enum_take)
      |> Stream.take(distance)
      |> Enum.filter(fn x -> x == 0 end)
      |> Enum.count()

    {dail_right |> Stream.drop(current_spot + distance) |> Enum.take(1) |> Enum.at(0), zeroes}
  end

  def rotate({:left, distance}, current_spot) do
    dail_left = Stream.cycle(99..0//-1 |> Enum.to_list())

    zeroes =
      dail_left
      |> Stream.drop(100 - current_spot)
      |> Stream.take(distance - 1)
      |> Enum.filter(fn x -> x == 0 end)
      |> Enum.count()

    {dail_left
     |> Stream.drop(99 - current_spot + distance)
     |> Enum.take(1)
     |> Enum.at(0), zeroes}
  end

  def follow_instructions([], _current_spot, password) do
    password
  end

  def follow_instructions([h | t], current_spot, password) do
    {new_spot, _zeroes} = rotate(h, current_spot)
    count = if new_spot == 0, do: 1, else: 0
    follow_instructions(t, new_spot, count + password)
  end

  def follow_instructions2([], _current_spot, password) do
    password
  end

  def follow_instructions2([h | t], current_spot, password) do
    {new_spot, zeroes} = rotate(h, current_spot)
    count = if new_spot == 0, do: 1, else: 0
    follow_instructions2(t, new_spot, count + password + zeroes)
  end

  def part1(instructions, start) do
    instructions
    |> follow_instructions(start, 0)
  end

  def part2(instructions, start) do
    instructions
    |> follow_instructions2(start, 0)
  end
end
