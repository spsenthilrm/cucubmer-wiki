Feature: Wiki Keyword search - Look up
  
  Scenario Outline:
    Given Enter search term '<searchTerm>'
    When Do search
    Then Multiple Look up for '<searchTerm>'  
    
    Examples:
      | searchTerm | result                     |
      | mercury    | Mercury usually refers to: |
      | max        | Max or MAX may refer to:   |
  Scenario Outline:
    Given Enter search term '<searchTerm>'
    When Do search
    Then Multiple results are shown for '<result>'

    Examples:
      | searchTerm | result                     |
      | mercury    | Mercury usually refers to: |
      | max        | Max or MAX may refer to:   |