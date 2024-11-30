defmodule Day01 do
  # An opening parenthesis, (, means he should go up one floor, and a closing parenthesis, ), means he should go down one floor.
  # last floor

  def navigate_floor([], current_floor_level) do
    current_floor_level
  end

  def navigate_floor([h | t], current_floor_level) do
    case h do
      ?( -> navigate_floor(t, current_floor_level + 1)
      ?) -> navigate_floor(t, current_floor_level - 1)
    end
  end

  def find_position(_lst, -1, position) do
    position
  end

  def find_position([h | t], current_floor_level, position) do
    case h do
      ?( -> find_position(t, current_floor_level + 1, position + 1)
      ?) -> find_position(t, current_floor_level - 1, position + 1)
    end
  end

  def part1_answer(input) do
    navigate_floor(input, 0)
  end

  def part2_answer(input) do
    find_position(input, 0, 0)
  end
end
