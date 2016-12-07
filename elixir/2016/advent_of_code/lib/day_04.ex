# Juan Vazquez
# http://javazquez.com
# http://adventofcode.com/2016/day/4

defmodule Day04 do
    
  def top5_freq(input_str) do
      input_str 
      |> String.graphemes
      |> Enum.sort
      |> Enum.filter(fn x -> x != "-" and !Regex.match?(~r{\d},x) end)
      |> Enum.chunk_by( &(&1))
      |> Enum.map(fn lst -> {Enum.count(lst), List.first(lst)} end)
      |> Enum.group_by( fn {x, _y} -> x end)
      |> Map.values
      |> Enum.sort(&(&1 > &2))
      |> List.flatten 
      |> Enum.take(5)
  end

  def is_valid_real_room?(str) do
    [freq_word, checksum, _] = str |> String.split(~r{\[|\]})
    top5_str = Enum.map_join(Day04.top5_freq(freq_word), fn {_cnt, alpha } -> alpha end)
    top5_str =~ ~r/#{checksum}/
  end
 
  def decrypt_code(code) do
    [word_str, _checksum, _]= code |> String.split(~r{\[|\]})
    {room_num, _} = String.slice(word_str, -3, 3) |> Integer.parse
    tokens = String.replace(word_str , "-#{room_num}", "") 
      |> String.graphemes  
    alphabet = "abcdefghijklmnopqrstuvwxyz" 
      |> String.graphemes
    shift = Stream.cycle(alphabet) 
      |> Stream.drop(rem(room_num, 26))
      |> Enum.take(26)
      
    {Enum.map_join(tokens, fn x -> new_char(["-"|alphabet], ["-" |shift], x) end) 
      |> String.replace("-"," "), room_num}
  end

  def new_char(alphabet, translated, char_to_match) do
    indexes = Enum.with_index(alphabet)
    {_alpha, pos} = Enum.find(indexes, fn {x, _y } -> x == char_to_match end)
    Enum.at translated, pos
  end


end
