# -*- coding: utf-8 -*-
tests = 0
passed = 0
failed = 0
passed = []

def start_high_level_test(testName):
    print('\n' + testName)
    print("-------------------------------------------")

def assertEquals(name, actual, expected, prepend='    '):
    try:
        if callable(actual): actual = actual(None)
    except Exception as e:
        actual = "Error: " + str(e)
    passed = actual == expected
    checkOrX = u"+ " if passed else "X "
    result_str = checkOrX + name +' Expected: ' + str(expected) + ' Actual: ' + str(actual)
    print(prepend + result_str)

def fail(name, error, prepend='    '):
     print(prepend + 'X ' + name + ' Error: ' + error)