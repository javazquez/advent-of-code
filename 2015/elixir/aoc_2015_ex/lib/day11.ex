defmodule Day11 do
  @number_letters %{
    0 => "a",
    1 => "b",
    2 => "c",
    3 => "d",
    4 => "e",
    5 => "f",
    6 => "g",
    7 => "h",
    8 => "i",
    9 => "j",
    10 => "k",
    11 => "l",
    12 => "m",
    13 => "n",
    14 => "o",
    15 => "p",
    16 => "q",
    17 => "r",
    18 => "s",
    19 => "t",
    20 => "u",
    21 => "v",
    22 => "w",
    23 => "x",
    24 => "y",
    25 => "z"
  }
  @letter_values %{
    "a" => 0,
    "b" => 1,
    "c" => 2,
    "d" => 3,
    "e" => 4,
    "f" => 5,
    "g" => 6,
    "h" => 7,
    "i" => 8,
    "j" => 9,
    "k" => 10,
    "l" => 11,
    "m" => 12,
    "n" => 13,
    "o" => 14,
    "p" => 15,
    "q" => 16,
    "r" => 17,
    "s" => 18,
    "t" => 19,
    "u" => 20,
    "v" => 21,
    "w" => 22,
    "x" => 23,
    "y" => 24,
    "z" => 25
  }

  def increment_number(num) do
    rem(num + 1, 26)
  end

  def increment_word([]), do: []

  def increment_word([h | t]) do
    new_h = increment_number(h)

    if new_h == 0 do
      [new_h | increment_word(t)]
    else
      [new_h | t]
    end
  end

  def ordered?(a, b, c) do
    a + 1 == b && b + 1 == c
  end

  def special_char?(num) do
    case num do
      8 -> true
      11 -> true
      14 -> true
      _ -> false
    end
  end

  # since I am working with a reversed list, list them in reverse
  def ordered_list?([h, g, f, e, d, c, b, a]) do
    ordered?(a, b, c) || ordered?(b, c, d) || ordered?(c, d, e) ||
      ordered?(d, e, f) || ordered?(e, f, g) || ordered?(f, g, h)
  end

  def valid?([a, b, c, d, e, f, g, h]) do
    order_val = ordered_list?([a, b, c, d, e, f, g, h])

    special_chars_value =
      Enum.map([a, b, c, d, e, f, g, h], &special_char?/1)
      |> Enum.any?()

    word =
      Enum.map([a, b, c, d, e, f, g, h], &Map.get(@number_letters, &1))
      |> Enum.join()

    dup_count = Regex.scan(~r/(.)\1/, word, capture: :first) |> Enum.count()

    order_val && !special_chars_value && dup_count >= 2
  end

  def word_to_numbers(word) do
    String.graphemes(word)
    |> Enum.map(&Map.get(@letter_values, &1))
  end

  def solve_part1(word) do
    word
    |> word_to_numbers()
    |> Enum.reverse()
    |> Stream.iterate(fn item ->
      item |> increment_word()
    end)
    |> Enum.find(fn item -> item |> valid?() end)
    |> Enum.map(&Map.get(@number_letters, &1))
    |> Enum.reverse()
    |> Enum.join()
  end

  def solve_part2(word) do
    word
    |> word_to_numbers()
    |> Enum.reverse()
    |> Stream.iterate(fn item ->
      item |> increment_word()
    end)
    |> Stream.filter(fn item -> item |> valid?() end)
    |> Stream.drop(1)
    |> Enum.take(1)
    |> Enum.at(0)
    |> Enum.map(&Map.get(@number_letters, &1))
    |> Enum.reverse()
    |> Enum.join()
  end
end
