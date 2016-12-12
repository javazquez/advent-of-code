# Juan Vazquez
# http://javazquez.com
# http://adventofcode.com/2016/day/7

defmodule Day07 do
    
    def convert_input_str_to_list(input) do
         ip = Regex.split(~r/\[(\w*)\]/,input)
            |> Enum.map(&String.graphemes/1)
         hypernet = Regex.scan( ~r{\[(\w+)\]}, input) 
            |> Enum.map(fn [_orig,txt] -> String.graphemes(txt) end)
        {ip,hypernet}
    end 

    def is_abba?(input_str) do
        {ip, hypernet} = convert_input_str_to_list(input_str)
        is_ip? = Enum.map(ip, &find_abba/1) 
          |> Enum.any?(fn x -> !is_nil(x) end )
        is_hyp? = Enum.map(hypernet, &find_abba/1) 
          |> Enum.all?(&is_nil/1) 
        is_ip? && is_hyp?
    end

    def find_abba(char_list) do
        char_list
        |> Stream.chunk(4,1)
        |> Enum.find(fn [a,b,c,d] ->  [a,b,c,d] == [d,c,b,a] &&  (a != b) end)
    end

    def find_aba(char_list) do
      char_list
        |> Stream.chunk(3,1)
        |> Enum.filter(fn [a,b,c] ->  [a,b,c] == [c,b,a] &&  (a != b) end)
    end

    def create_bab([a, b, a]), do: [b,a,b]

    def is_aba?(input_str) do
      { ip, hypernet} = convert_input_str_to_list(input_str)
      all_aba = Enum.flat_map(ip, &find_aba/1) 
      all_bab = Enum.map(all_aba, &create_bab/1)
      hypernet_aba = Enum.flat_map(hypernet, &(find_aba &1)) 
      MapSet.intersection( MapSet.new(hypernet_aba), MapSet.new(all_bab)) 
        |> MapSet.size > 0
    end

end

