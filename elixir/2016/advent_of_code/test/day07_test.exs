# Juan Vazquez
# http://javazquez.com
# http://adventofcode.com/2016/day/7

defmodule Day07Test do
  use ExUnit.Case
  doctest Day07

  test "example input 1" do
    assert Day07.is_abba?("abba[mnop]qrst")
  end

  test "example input 2" do
    assert Day07.is_abba?("abcd[bddb]xyyx") == false    
  end
  
  test "example input 3" do
    assert Day07.is_abba?("aaaa[qwer]tyui") == false    
  end

  test "example input 4" do
    assert Day07.is_abba?("ioxxoj[asdfgh]zxcvbn")    
  end

  test "input case 5 " do
    assert !Day07.is_abba?("macvtmbchbanwchhsnwnqixjgwhcrpfeun[mvseymbltdzywnw]xbogzgtddtzzadgsrin[sibgoazaxuyfaaf]tdtrrjbxjzusuvzogpa[etytgiqwoyxevcq]") 
  end

  test "Solution one with file input" do
    {:ok, file_text} = File.read "test/support/day_07_input.txt"
    input = file_text      
      |> String.split(~r{\n})
    assert Enum.filter(input, &Day07.is_abba?/1) |> Enum.count == 110
  end

  test "Prob 2 input 1" do
    assert Day07.is_aba? "aba[bab]xyz"
  end

  test "Prob 2 input 2" do
    assert !Day07.is_aba?("xyx[xyx]xyx")
  end

  test "Prob 2 input 3" do
    assert Day07.is_aba? "aaa[kek]eke"
  end

  test "Prob 2 input 4" do
    assert Day07.is_aba? "zazbz[bzb]cdb"
  end

  test "Prob 2 input 4b" do
    assert Day07.is_aba? "za[adaed]ealsflasjfoiejf[bbbssccs][bzb]cdbzbz"
  end

  test "this should work but wasn't (sus usu)" do
     assert Day07.is_aba? "mrlrhbmhyegvyobjsg[deqoctrkekususgjm]fhxydpcmqstrssd[zpzkryziyeeuagp]vfllzbrkzqysusj[fgzaapjznvxkkijhfne]xgslyehnivgjwlacf" 
  end

  test "Solution 2 with file input" do
    {:ok, file_text} = File.read "test/support/day_07_input.txt"
    input = file_text |> String.split(~r{\n})
    assert Enum.filter(input, &Day07.is_aba?/1) |> Enum.count == 242
  end

end
