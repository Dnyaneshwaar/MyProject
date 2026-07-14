#Fixtures - create reusable code & use where ever required
import pytest


@pytest.fixture(scope='module')
def preWork():
    print('I setup module instance')
    return "fail"


@pytest.fixture(scope='function')
def secondWork():
    print('I setup secondWork instance')
    yield
    print("tear down validation")



def test_initialCheck(preWork,secondWork):
    print('This is first test')
    assert preWork == "fail"


@pytest.mark.skip
def test_SecondCheck(preSetupWork,secondWork):
    print('This is Second test')
